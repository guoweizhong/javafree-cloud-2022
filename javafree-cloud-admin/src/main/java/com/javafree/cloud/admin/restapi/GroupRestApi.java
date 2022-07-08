package com.javafree.cloud.admin.restapi;

import com.javafree.cloud.admin.entity.Group;
import com.javafree.cloud.admin.service.GroupService;
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
 * @Date 2022/7/5 14:09
 */

@Slf4j
@RestController
@Api(value = "用户组管理相关接口", tags = {"用户组管理相关接口"})
@RequestMapping("/group")
public class GroupRestApi {
  @Autowired
  GroupService groupService;

  @ApiOperation(value = "删除用户组", notes = "删除用户组")
  @DeleteMapping("/deleteGroup")
  public RestApiResponse deleteGroup(@RequestParam(name = "id", required = true) String groupid) {
    groupService.deleteGroup(groupid);
    log.info("成功删除用户组信息，用户组ID={}", groupid);
    return RestApiResponse.OK("成功删除用户组信息!");
  }

  @ApiOperation(value = "批量删除用户组信息", notes = "用户组ids用逗号(,)分隔")
  @DeleteMapping("/deleteGroupByIds")
  public RestApiResponse deleteGroupByIds(@RequestParam(name = "ids", required = true) String ids) {
    groupService.deleteGroupByIds(Arrays.asList(ids.split(",")));
    log.info("成功批量删除用户组信息，用户组IDS={}", ids);
    return RestApiResponse.OK("成功批量删除用户组信息!");
  }

  @ApiOperation(value = "获得用户组信息")
  @GetMapping("/getGroupById")
  public RestApiResponse<Group> getGroupById(@RequestParam("id") String id) {
    Group orgGroup = groupService.getGroupById(id);
    if (null == orgGroup) {
      log.info("未找到用户组信息，用户组ID={}", id);
      return RestApiResponse.WARNING("未找到用户组信息！", HttpStatus.NO_CONTENT);
    }
    log.info("找到用户组信息，用户组对象为:{}", JsonUtils.getJsonStringFromObject(orgGroup));
    return RestApiResponse.OK(orgGroup);
  }

  @ApiOperation(value = "查询用户组信息列表,多条件关系为and", notes = "查询用户组信息列表,多条件关系为and")
  @PostMapping("/findGroupsByGroup")
  public RestApiResponse<PageResult<Group>> findGroupsByGroup(@RequestBody RestApiParamBody<Group> apiParam) {

    PageResult<Group> groupPage = groupService.findGroupsByGroup(apiParam.getDataParam(), apiParam.getPageParam());
    if (null == groupPage || groupPage.getTotalElements() < 1) {
      log.info("未找到用户组信息,传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
      return RestApiResponse.WARNING("未找到用户组信息!", HttpStatus.NO_CONTENT);
    }
    log.info("找到用户组信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
    return RestApiResponse.OK(groupPage);
  }

  @ApiOperation(value = "查询用户组信息列表,多条件关系为OR",notes = "查询用户组信息列表,多条件关系为OR")
  @PostMapping("/findGroupsByGroupAny")
  public RestApiResponse<PageResult<Group>> findGroupsByGroupAny(@RequestBody RestApiParamBody<Group> apiParam) {

    //为了前端提供关键字查询
    Group group=apiParam.getDataParam();
    if (StringUtils.hasText(group.getGroupName())){
      //额外增加组描述字段
      group.setDescription(group.getGroupName());
      apiParam.setDataParam(group);
    }
    PageResult<Group>  groupPage=groupService.findGroupsByGroupAny(apiParam.getDataParam(),apiParam.getPageParam());
    if (null==groupPage || groupPage.getTotalElements()<1) {
      log.info("未找到用户组信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam));
      return  RestApiResponse.WARNING("未找到用户组信息!", HttpStatus.NO_CONTENT);
    }
    log.info("找到用户组信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
    return  RestApiResponse.OK(groupPage);
  }

  @ApiOperation(value = "新增或更新用户组信息", notes = "新增或更新用户组信息")
  @PostMapping("/saveGroup")
  public RestApiResponse<Group> saveGroup(@Valid @RequestBody Group group) {
    Group orgGroup = groupService.saveGroup(group);
    log.info("用户组信息保存成功，用户组对象为:{}", JsonUtils.getJsonStringFromObject(group));
    return RestApiResponse.OK(orgGroup);
  }
}
