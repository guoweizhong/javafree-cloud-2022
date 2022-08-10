package com.javafree.cloud.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * @version V1.0
 * @Description: 客户端传登录信息的传参对象
 * @Author gwz  gwz126@126.com
 * @Date 2022/4/15 14:14
 */
@Schema(name = " LoginVo对象 ", description = "LoginVo对象,用于登录页面传参")
public class LoginVo implements Serializable {

    @Schema(name = "username", description = "登录名")
    String username;
    @Schema(name = "password", description = "登录密码")
    String password;
    @Schema(name = "autoLogin", description = "是否自动登录")
    boolean  autoLogin;
    @Schema(name = "type", description = "登录账号类型 mobile:手机或account:用户名")
    String  type;
    @Schema(name = "mobile", description = "手机号")
    String mobile;
    @Schema(name = "captcha", description = "手机验证码")
    String captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
