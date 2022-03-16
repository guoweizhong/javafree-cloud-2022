package com.javafree.cloud.admin.controller;

import com.javafree.cloud.admin.entity.User;
import com.javafree.cloud.admin.service.UserService;
import com.javafree.cloud.admin.vo.UserVo;
import com.javafree.cloud.common.api.ApiParamBody;
import com.javafree.cloud.common.api.ApiResponse;
import com.javafree.cloud.common.utils.JavaFreeBeanUtils;
import com.javafree.cloud.common.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/24 18:09
 */
@Slf4j
@RestController
@Api(tags="用户管理API")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", dataTypeClass = String.class)
    })
    @DeleteMapping("/deleteUser")
    public ApiResponse deleteUser(@RequestParam(name = "userid", required = true) String userid){
        userService.deleteUser(userid);
        log.info("成功删除用户信息，用户ID={}",userid);
        return  ApiResponse.OK("成功删除用户信息!");
    }

    @ApiOperation(value = "批量删除用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Userids", value = "用户ids用逗号(,)分隔", dataTypeClass = String.class)
    })
    @DeleteMapping("/deleteUserByIds")
    public ApiResponse deleteUserByIds(@RequestParam(name = "userids", required = true) String userids){
        userService.deleteUserByIds(Arrays.asList(userids.split(",")));
        log.info("成功批量删除用户信息，用户IDS={}",userids);
        return  ApiResponse.OK("成功批量删除用户信息!");
    }

    @ApiOperation(value = "通过真实姓名查询用户信息列表(模糊查询)")
    @PostMapping("/findUsersByRealname")
    public ApiResponse<Page<User>> findUsersByRealname( @RequestBody(required = false) ApiParamBody<String> apiParam) {

        Page<User>  userPage=userService.findUsersByRealname(apiParam.getDataParam(),apiParam.getPageParam());
        if (null==userPage || userPage.getTotalElements()<1) {
            log.info("未找到用户信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
            return  ApiResponse.WARNING("未找到用户信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到用户信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
        return  ApiResponse.OK(userPage);
    }


    @ApiOperation(value = "获得用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userid", value = "用户id", dataTypeClass = String.class)
    })
    @GetMapping("/getUserById")
    public ApiResponse<User> getUserById(@RequestParam("userid") String userid){
        User orgUser=userService.getUserById(userid);
        if (null==orgUser) {
            log.info("未找到用户信息，用户ID={}",userid);
            return  ApiResponse.WARNING("未找到用户信息！", HttpStatus.NO_CONTENT);
        }
        log.info("找到用户信息，用户对象为:{}",JsonUtils.getJsonStringFromObject(orgUser));
        return  ApiResponse.OK(orgUser);
    }

    @ApiOperation(value = "通过用户名获得用户信息")
    @GetMapping("/getUserByName")
    public ApiResponse<User> getUserByName(@RequestParam("username") String username){
        User user=userService.getUserByName(username);
        if (null==user) {
            log.info("未找到用户信息，用户登录名={}",username);
            return  ApiResponse.WARNING("未找到用户信息！", HttpStatus.NO_CONTENT);
        }
        log.info("找到用户信息，用户对象为:{}",JsonUtils.getJsonStringFromObject(user));
        return  ApiResponse.OK(user);
    }

    @ApiOperation(value = "新增或更新用户信息，不包括用户密码字段")
    @PostMapping("/savaUser")
    //通过@Valid 开启属性值格式校验
    public ApiResponse<User> savaUser(@Valid @RequestBody User user){
        User orgUser=userService.saveUser(user);
        log.info("用户信息保存成功，用户对象为:{}",JsonUtils.getJsonStringFromObject(user));
        return  ApiResponse.OK(orgUser);
    }

    @ApiOperation(value = "新增或更新用户信息,包括用户密码字段")
    @PostMapping("/savaUserVo")
    //通过@Valid 开启属性值格式校验
    public ApiResponse<UserVo> savaUserVo(@Valid @RequestBody UserVo userVo){
        Assert.notNull(userVo, "UserVo 对象不能为空.");
        User tempUser=new User();
        //将传入的UserVo对象值copy到tempUser对象中，并忽略UserVo对象为空的属性
        BeanUtils.copyProperties(userVo,tempUser, JavaFreeBeanUtils.getNullPropertyNames(userVo));

        User orgUser=userService.saveUser(tempUser);
        log.info("用户信息保存成功，用户对象为:{}",JsonUtils.getJsonStringFromObject(userVo));

        //将数据库返回的对象再传给接口调用方
        BeanUtils.copyProperties(orgUser,userVo, JavaFreeBeanUtils.getNullPropertyNames(userVo));

        return  ApiResponse.OK(userVo);
    }


    @ApiOperation(value = "查询用户信息列表,多条件关系为and")
    @PostMapping("/findUsersByUser")
    public ApiResponse<Page<User>> findUsersByUser(@RequestBody(required = false) ApiParamBody<User> apiParam, HttpServletRequest request) {

        Page<User>  userPage=userService.findUsersByUser(apiParam.getDataParam(),apiParam.getPageParam());
        if (null==userPage || userPage.getTotalElements()<1) {
             log.info("未找到用户信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
           return  ApiResponse.WARNING("未找到用户信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到用户信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
        return  ApiResponse.OK(userPage);
    }

    @ApiOperation(value = "查询用户信息列表,多条件关系为OR")
    @PostMapping("/findUsersByUserAny")
    public ApiResponse<Page<User>> findUsersByUserAny(@RequestBody(required = false) ApiParamBody<User> apiParam, HttpServletRequest request) {

        Page<User>  userPage=userService.findUsersByUserAny(apiParam.getDataParam(),apiParam.getPageParam());
        if (null==userPage || userPage.getTotalElements()<1) {
            log.info("未找到用户信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
            return  ApiResponse.WARNING("未找到用户信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到用户信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
        return  ApiResponse.OK(userPage);
    }
}
