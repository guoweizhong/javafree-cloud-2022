package com.javafree.cloud.admin.restapi;

import com.javafree.cloud.admin.entity.Tenant;
import com.javafree.cloud.admin.service.TenantService;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.api.RestApiParamBody;
import com.javafree.cloud.common.api.RestApiResponse;
import com.javafree.cloud.common.utils.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @version V1.0
 * @Description:租户管理相关接口
 * @Author gwz  gwz126@126.com
 * @Date 2022/4/22 15:31
 */
@Slf4j
@RestController
@Tag(name = "TenantRestApi", description ="租户管理接口")
@RequestMapping("/tenant")
public class TenantRestApi {
    @Autowired
    TenantService tenantService ;

    @Operation(summary = "删除租户", description = "删除租户")
    @DeleteMapping("/deleteTenant")
    public RestApiResponse deleteTenant(@RequestParam(name = "id", required = true) String tenantid){
        tenantService.deleteTenant(tenantid);
        log.info("成功删除租户信息，租户ID={}",tenantid);
        return  RestApiResponse.OK("成功删除租户信息!");
    }

    @Operation(summary = "批量删除租户信息", description = "租户ids用逗号(,)分隔")
    @DeleteMapping("/deleteTenantByIds")
    public RestApiResponse deleteTenantByIds(@RequestParam(name = "ids", required = true) String ids){
        tenantService.deleteTenantByIds(Arrays.asList(ids.split(",")));

        log.info("成功批量删除租户信息，租户IDS={}",ids);
        return  RestApiResponse.OK("成功批量删除租户信息!");
    }

    @Operation(summary = "获得租户信息")
    @GetMapping("/getTenantById")
    public RestApiResponse<Tenant> getTenantById(@RequestParam("id") String id){
        Tenant orgTenant=tenantService.getTenantById(id);
        if (null==orgTenant) {
            log.info("未找到租户信息，租户ID={}",id);
            return  RestApiResponse.WARNING("未找到租户信息！", HttpStatus.NO_CONTENT);
        }
        log.info("找到租户信息，租户对象为:{}", JsonUtils.getJsonStringFromObject(orgTenant));
        return  RestApiResponse.OK(orgTenant);
    }

    @Operation(summary = "查询租户信息列表,多条件关系为and", description = "查询租户信息列表,多条件关系为and")
    @PostMapping("/findTenantsByTenant")
    public RestApiResponse<PageResult<Tenant>> findTenantsByTenant(@RequestBody RestApiParamBody<Tenant> apiParam) {

        PageResult<Tenant>  tenantPage=tenantService.findTenantsByTenant(apiParam.getDataParam(),apiParam.getPageParam());
        if (null==tenantPage || tenantPage.getTotalElements()<1) {
            log.info("未找到租户信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
            return  RestApiResponse.WARNING("未找到租户信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到租户信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
        return  RestApiResponse.OK(tenantPage);
    }

    @Operation(summary = "新增或更新租户信息",description = "新增或更新租户信息")
    @PostMapping("/saveTenant")
    //通过@Valid 开启属性值格式校验
    public RestApiResponse<Tenant> saveTenant(@Valid @RequestBody Tenant tenant){
        Tenant orgTenant=tenantService.saveTenant(tenant);
        log.info("租户信息保存成功，租户对象为:{}",JsonUtils.getJsonStringFromObject(tenant));
        return  RestApiResponse.OK(orgTenant);
    }

}
