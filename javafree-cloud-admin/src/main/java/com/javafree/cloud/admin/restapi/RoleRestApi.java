package com.javafree.cloud.admin.restapi;

import com.javafree.cloud.admin.entity.Role;
import com.javafree.cloud.admin.service.RoleService;
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
import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/6/7 16:50
 */
@Slf4j
@RestController
@Tag(name = "RoleRestApi", description ="角色管理接口")
@RequestMapping("/role")
public class RoleRestApi {
  @Autowired
  RoleService roleService ;

  @Operation(summary = "删除角色", description = "删除角色")
  @DeleteMapping("/deleteRole")
  public RestApiResponse deleteRole(@RequestParam(name = "id", required = true) String roleid){
    roleService.deleteRole(roleid);
    log.info("成功删除角色信息，角色ID={}",roleid);
    return  RestApiResponse.OK("成功删除角色信息!");
  }

  @Operation(summary = "批量删除角色信息", description = "角色ids用逗号(,)分隔")
  @DeleteMapping("/deleteRoleByIds")
  public RestApiResponse deleteRoleByIds(@RequestParam(name = "ids", required = true) String ids){
    roleService.deleteRoleByIds(Arrays.asList(ids.split(",")));
    log.info("成功批量删除角色信息，角色IDS={}",ids);
    return  RestApiResponse.OK("成功批量删除角色信息!");
  }

  @Operation(summary = "获得角色信息")
  @GetMapping("/getRoleById")
  public RestApiResponse<Role> getRoleById(@RequestParam("id") String id){
    Role orgRole=roleService.getRoleById(id);
    if (null==orgRole) {
      log.info("未找到角色信息，角色ID={}",id);
      return  RestApiResponse.WARNING("未找到角色信息！", HttpStatus.NO_CONTENT);
    }
    log.info("找到角色信息，角色对象为:{}", JsonUtils.getJsonStringFromObject(orgRole));
    return  RestApiResponse.OK(orgRole);
  }

  @Operation(summary = "查询角色信息列表,多条件关系为and", description = "查询角色信息列表,多条件关系为and")
  @PostMapping("/findRolesByRole")
  public RestApiResponse<PageResult<Role>> findRolesByRole(@RequestBody RestApiParamBody<Role> apiParam) {
    PageResult<Role>  rolePage=roleService.findRolesByRole(apiParam.getDataParam(),apiParam.getPageParam());
    if (null==rolePage || rolePage.getTotalElements()<1) {
      log.info("未找到角色信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
      return  RestApiResponse.WARNING("未找到角色信息!", HttpStatus.NO_CONTENT);
    }
    log.info("找到角色信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
    return  RestApiResponse.OK(rolePage);
  }

  @Operation(summary = "获得角色列表", description = "获得角色列表")
  @PostMapping("/getRolesList")
  public RestApiResponse<List<Role>> getRolesList(Role role) {
    List<Role>  roleList=roleService.getRolesListByRole(role);
    if (null==roleList || roleList.size()<1) {
      log.info("未找到角色信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(role));
      return  RestApiResponse.WARNING("未找到角色信息!", HttpStatus.NO_CONTENT);
    }
    log.info("找到角色信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(role));
    return  RestApiResponse.OK(roleList);
  }


  @Operation(summary = "新增或更新角色信息",description = "新增或更新角色信息")
  @PostMapping("/saveRole")
  //通过@Valid 开启属性值格式校验
  public RestApiResponse<Role> saveRole(@Valid @RequestBody Role role){
    Role orgRole=roleService.saveRole(role);
    log.info("角色信息保存成功，角色对象为:{}",JsonUtils.getJsonStringFromObject(role));
    return  RestApiResponse.OK(orgRole);
  }

  @Operation(summary = "获得角色类型分组信息",description = "获得角色类型分组信息")
  @GetMapping("/countRolesByType")
  public RestApiResponse<List<Object[]>> countRolesByType(){
     List<Object[]> countRoles=roleService.countRolesByType();
    return  RestApiResponse.OK(countRoles);
  }

}
