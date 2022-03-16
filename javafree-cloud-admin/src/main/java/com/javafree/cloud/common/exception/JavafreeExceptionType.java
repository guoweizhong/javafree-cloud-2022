package com.javafree.cloud.common.exception;

/**
 * 平台异常枚举类型
 *
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/1 14:26
 */

public enum JavafreeExceptionType {
    CLIENT_ERROR(400, "您输入的数据错误或没有权限访问资源!"),
    SERVER_ERROR(500, "系统出现异常，请您稍后再试或联系管理员!"),
    OTHER_ERROR(600, "系统出现未知异常，请联系管理员!");

    //异常信息描述
    private String msg;
    //异常信息编码
    private int code;

    JavafreeExceptionType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }


}
