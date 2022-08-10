package com.javafree.cloud.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * @version V1.0
 * @Description: 用于登录返回登录结果
 * @Author gwz  gwz126@126.com
 * @Date 2022/4/15 14:14
 */
@Schema(name = " LoginResultVo对象 ", description = "LoginResultVo对象,用于登录返回登录结果")
public class LoginResultVo implements Serializable {
    @Schema(name = "status", description = "返回登录状态 'ok' | 'error'")
    String status;
    @Schema(name = "currentAuthority", description = "返回用户身份 'user' | 'guest' | 'admin'")
    String currentAuthority;
    @Schema(name = "type", description = "登录账号类型 mobile:手机或account:用户名")
    String  type;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentAuthority() {
        return currentAuthority;
    }

    public void setCurrentAuthority(String currentAuthority) {
        this.currentAuthority = currentAuthority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
