package com.javafree.cloud.common.exception;

/**
 * 平台统一异常类
 *
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/1 14:48
 */

public class JavafreeException extends RuntimeException {

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    //异常信息描述
    private String message;
    //异常信息编码
    private int code;

    //约束程序员不能自己创建异常
    private JavafreeException() {
    }

    public JavafreeException(JavafreeExceptionType javafreeExceptionType) {
        this.code = javafreeExceptionType.getCode();
        this.message = javafreeExceptionType.getMsg();
    }

    public JavafreeException(JavafreeExceptionType javafreeExceptionType, String message) {
        this.code = javafreeExceptionType.getCode();
        this.message = message;
    }
}
