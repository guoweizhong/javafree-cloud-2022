package com.javafree.cloud.admin.restapi;

import com.javafree.cloud.admin.entity.GroupRel;
import com.javafree.cloud.admin.service.GroupRelService;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.api.RestApiParamBody;
import com.javafree.cloud.common.api.RestApiResponse;
import com.javafree.cloud.common.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/7 18:01
 */
@Slf4j
@RestController
@Api(value = "用户组管理相关接口", tags = {"用户组管理相关接口"})
@RequestMapping("/grouprel")
public class GroupRelRestApi {
    @Autowired
    GroupRelService groupRelService;

    @ApiOperation(value = "删除组成员关系", notes = "删除组成员关系")
    @DeleteMapping("/deleteGroupRel")
    public RestApiResponse deleteGroupRel(@RequestParam(name = "id", required = true) String groupRelid) {
        groupRelService.deleteGroupRelById(groupRelid);
        log.info("成功删除组成员关系信息，组成员关系ID={}", groupRelid);
        return RestApiResponse.OK("成功删除组成员关系信息!");
    }

    @ApiOperation(value = "批量删除组成员关系信息", notes = "组成员关系ids用逗号(,)分隔")
    @DeleteMapping("/deleteGroupRelByIds")
    public RestApiResponse deleteGroupRelByIds(@RequestParam(name = "ids", required = true) String ids) {
        groupRelService.deleteGroupRelByIds(Arrays.asList(ids.split(",")));
        log.info("成功批量删除组成员关系信息，组成员关系IDS={}", ids);
        return RestApiResponse.OK("成功批量删除组成员关系信息!");
    }

    @ApiOperation(value = "获得组成员关系信息")
    @GetMapping("/getGroupRelById")
    public RestApiResponse<GroupRel> getGroupRelById(@RequestParam("id") String id) {
        GroupRel orgGroupRel = groupRelService.getGroupRelById(id);
        if (null == orgGroupRel) {
            log.info("未找到组成员关系信息，组成员关系ID={}", id);
            return RestApiResponse.WARNING("未找到组成员关系信息！", HttpStatus.NO_CONTENT);
        }
        log.info("找到组成员关系信息，组成员关系对象为:{}", JsonUtils.getJsonStringFromObject(orgGroupRel));
        return RestApiResponse.OK(orgGroupRel);
    }

    @ApiOperation(value = "查询组成员关系信息列表,多条件关系为and", notes = "查询组成员关系信息列表,多条件关系为and")
    @PostMapping("/findGroupRelsByGroupRel")
    public RestApiResponse<PageResult<GroupRel>> findGroupRelsByGroupRel(@RequestBody RestApiParamBody<GroupRel> apiParam) {

        PageResult<GroupRel> groupRelPage = groupRelService.findGroupRelsByGroupRel(apiParam.getDataParam(), apiParam.getPageParam());
        if (null == groupRelPage || groupRelPage.getTotalElements() < 1) {
            log.info("未找到组成员关系信息,传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
            return RestApiResponse.WARNING("未找到组成员关系信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到组成员关系信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
        return RestApiResponse.OK(groupRelPage);
    }

    @ApiOperation(value = "查询组成员关系信息列表,多条件关系为OR",notes = "查询组成员关系信息列表,多条件关系为OR")
    @PostMapping("/findGroupRelsByGroupRelAny")
    public RestApiResponse<PageResult<GroupRel>> findGroupRelsByGroupRelAny(@RequestBody RestApiParamBody<GroupRel> apiParam) {

        //为了前端提供关键字查询
        GroupRel groupRel=apiParam.getDataParam();
        if (StringUtils.hasText(groupRel.getGroupName())){
            //额外增加组描述字段
            groupRel.setObjName(groupRel.getGroupName());
            apiParam.setDataParam(groupRel);
        }
        PageResult<GroupRel>  groupRelPage=groupRelService.findGroupRelsByGroupRelAny(apiParam.getDataParam(),apiParam.getPageParam());
        if (null==groupRelPage || groupRelPage.getTotalElements()<1) {
            log.info("未找到组成员关系信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam));
            return  RestApiResponse.WARNING("未找到组成员关系信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到组成员关系信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
        return  RestApiResponse.OK(groupRelPage);
    }

    @ApiOperation(value = "新增或更新组成员关系信息", notes = "新增或更新组成员关系信息")
    @PostMapping("/saveGroupRel")
    public RestApiResponse<GroupRel> saveGroupRel(@Valid @RequestBody GroupRel groupRel) {
        GroupRel orgGroupRel = groupRelService.saveGroupRel(groupRel);
        log.info("组成员关系信息保存成功，组成员关系对象为:{}", JsonUtils.getJsonStringFromObject(groupRel));
        return RestApiResponse.OK(orgGroupRel);
    }
}
