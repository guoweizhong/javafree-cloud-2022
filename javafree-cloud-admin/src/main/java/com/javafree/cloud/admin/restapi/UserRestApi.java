package com.javafree.cloud.admin.restapi;

import com.javafree.cloud.admin.entity.User;
import com.javafree.cloud.admin.service.UserService;
import com.javafree.cloud.admin.vo.UserVo;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.api.RestApiParamBody;
import com.javafree.cloud.common.api.RestApiResponse;
import com.javafree.cloud.common.utils.JavaFreeBeanUtils;
import com.javafree.cloud.common.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @version V1.0
 * @Description: 用户管理相关接口
 * @Author gwz  gwz126@126.com
 * @Date 2022/3/24 15:21
 */
@Slf4j
@RestController
@Api(value = "用户管理相关接口", tags = {"用户管理相关接口"})
@RequestMapping("/user")
public class UserRestApi {
  @Autowired
  UserService userService;

  @ApiOperation(value = "删除用户", notes = "删除用户")
  @DeleteMapping("/deleteUser")
  public RestApiResponse deleteUser(@RequestParam(name = "userid", required = true) String userid){
    userService.deleteUser(userid);
    log.info("成功删除用户信息，用户ID={}",userid);
    return  RestApiResponse.OK("成功删除用户信息!");
  }

  @ApiOperation(value = "批量删除用户信息", notes = "用户ids用逗号(,)分隔")
  @DeleteMapping("/deleteUserByIds")
  public RestApiResponse deleteUserByIds(@RequestParam(name = "userids", required = true) String userids){
    userService.deleteUserByIds(Arrays.asList(userids.split(",")));
    log.info("成功批量删除用户信息，用户IDS={}",userids);
    return  RestApiResponse.OK("成功批量删除用户信息!");
  }




  @ApiOperation(value = "获得用户信息")
  @GetMapping("/getUserById")
  public RestApiResponse<User> getUserById(@RequestParam("userid") String userid){
    User orgUser=userService.getUserById(userid);
    if (null==orgUser) {
      log.info("未找到用户信息，用户ID={}",userid);
      return  RestApiResponse.WARNING("未找到用户信息！", HttpStatus.NO_CONTENT);
    }
    log.info("找到用户信息，用户对象为:{}",JsonUtils.getJsonStringFromObject(orgUser));
    return  RestApiResponse.OK(orgUser);
  }

  @ApiOperation(value = "通过用户名获得用户信息")
  @GetMapping("/getUserByName")
  public RestApiResponse<User> getUserByName(@RequestParam("username") String username){
    User user=userService.getUserByName(username);
    if (null==user) {
      log.info("未找到用户信息，用户登录名={}",username);
      return  RestApiResponse.WARNING("未找到用户信息！", HttpStatus.NO_CONTENT);
    }
    log.info("找到用户信息，用户对象为:{}",JsonUtils.getJsonStringFromObject(user));
    return  RestApiResponse.OK(user);
  }

  @ApiOperation(value = "通过真实姓名查询用户信息列表(模糊查询)", notes = "通过真实姓名查询用户信息列表(模糊查询)")
  @PostMapping("/findUsersByRealname")
  public RestApiResponse<PageResult<User>> findUsersByRealname(@RequestBody(required = false) RestApiParamBody<String> apiParam) {
    //分类数据
    PageResult<User> userPage=userService.findUsersByRealname(apiParam.getDataParam(),apiParam.getPageParam());
    if (null==userPage || userPage.getTotalElements()<1) {
      log.info("未找到用户信息,传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
      return  RestApiResponse.WARNING("未找到用户信息!", HttpStatus.NO_CONTENT);
    }
    log.info("找到用户信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
    return  RestApiResponse.OK(userPage);
  }
  @ApiOperation(value = "查询用户信息列表,多条件关系为and", notes = "查询用户信息列表,多条件关系为and")
  @PostMapping("/findUsersByUser")
  public RestApiResponse<PageResult<User>> findUsersByUser(@RequestBody RestApiParamBody<User> apiParam) {

    PageResult<User>  userPage=userService.findUsersByUser(apiParam.getDataParam(),apiParam.getPageParam());
    if (null==userPage || userPage.getTotalElements()<1) {
      log.info("未找到用户信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
      return  RestApiResponse.WARNING("未找到用户信息!", HttpStatus.NO_CONTENT);
    }
    log.info("找到用户信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
    return  RestApiResponse.OK(userPage);
  }

  @ApiOperation(value = "查询用户信息列表,多条件关系为OR",notes = "查询用户信息列表,多条件关系为OR")
  @PostMapping("/findUsersByUserAny")
  public RestApiResponse<PageResult<User>> findUsersByUserAny(@RequestBody RestApiParamBody<User> apiParam) {

    PageResult<User>  userPage=userService.findUsersByUserAny(apiParam.getDataParam(),apiParam.getPageParam());
    if (null==userPage || userPage.getTotalElements()<1) {
      log.info("未找到用户信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam));
      return  RestApiResponse.WARNING("未找到用户信息!", HttpStatus.NO_CONTENT);
    }
    log.info("找到用户信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
    return  RestApiResponse.OK(userPage);
  }


  /**
   *
   *  因将user对象的 password 属性增加了@JsonIgnore注解，
   *  从前端无法获得和返回user 的password属性。
   *  此接口用于对User对象，除密码以外属性操作
   * @param user
   * @return
   */
  @ApiOperation(value = "新增或更新用户信息，不包括用户密码字段",notes = "新增或更新用户信息，不包括用户密码字段")
  @PostMapping("/savaUser")
  //通过@Valid 开启属性值格式校验
  public RestApiResponse<User> savaUser(@Valid @RequestBody User user){
    User orgUser=userService.saveUser(user);
    log.info("用户信息保存成功，用户对象为:{}",JsonUtils.getJsonStringFromObject(user));
    return  RestApiResponse.OK(orgUser);
  }

  /**
   * 因将user对象的 password 属性增加了@JsonIgnore注解，
   *  从前端无法获得和返回user 的password属性。
   *  此接口用于对User对象，除密码以外属性操作
   * @param userVo
   * @return
   */
  @ApiOperation(value = "新增或更新用户信息,包括用户密码字段",notes = "新增或更新用户信息,包括用户密码字段")
  @PostMapping("/savaUserVo")
  //通过@Valid 开启属性值格式校验
  public RestApiResponse<UserVo> savaUserVo(@Valid @RequestBody UserVo userVo){
    Assert.notNull(userVo, "UserVo 对象不能为空.");
    User tempUser=new User();
    //将传入的UserVo对象值copy到tempUser对象中，并忽略UserVo对象为空的属性
    BeanUtils.copyProperties(userVo,tempUser, JavaFreeBeanUtils.getNullPropertyNames(userVo));

    User orgUser=userService.saveUser(tempUser);
    log.info("用户信息保存成功，用户对象为:{}",JsonUtils.getJsonStringFromObject(userVo));

    //将数据库返回的对象再传给接口调用方
    BeanUtils.copyProperties(orgUser,userVo, JavaFreeBeanUtils.getNullPropertyNames(userVo));

    return  RestApiResponse.OK(userVo);
  }


}