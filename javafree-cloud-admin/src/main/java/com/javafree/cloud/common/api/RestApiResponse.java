package com.javafree.cloud.common.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import com.javafree.cloud.common.exception.*;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @version V1.0
 * @Description: 在 Spring Boot 中使用 ResponseEntity 自定义 JSON 响应
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/23 18:05
 */

@ApiModel(value = "接口返回统一JSON对象", description = "接口返回统一JSON对象，规范返回JSON数据格式")
public class RestApiResponse<T> implements Serializable {
    private static final long serialVersionUID = -7172507203452011166L;
    /**
     * 响应码
     */
    @ApiModelProperty("HTTP响应码")
    private int status;

    /**
     * 是否成功
     */
    @ApiModelProperty("是否成功")
    private boolean success;

    /**
     * 响应消息
     */
    @ApiModelProperty("响应提示消息")
    private String message;

    /**
     * 响应数据
     */
    @ApiModelProperty("响应业务数据")
    private T data;

    /**
     * 返回结果的服务器IP
     */
    @ApiModelProperty("返回结果的服务器IP")
    private String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 响应时间
     */
    @ApiModelProperty("响应时间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public RestApiResponse<T>  setData(T data) {
        this.data = data;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public RestApiResponse() {
        timestamp = new Date();
    }

    /**
     * 包含泛型元素
     * @param data
     * @param msg
     * @param status
     * @param <T>
     * @return
     */
    public static <T> RestApiResponse<T> result(T data, String msg, HttpStatus status) {
        RestApiResponse<T> r = new RestApiResponse<T>();
        r.setStatus(status.value());
        r.setSuccess(status.value()>=200 & status.value()<300 ? true : false);
        r.setMessage(msg);
        r.setHost(getIp());
        r.setTimestamp(new Date());
        r.setData(data);
        return r;
    }

    private static String getIp()   {
        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 不包含泛型元素
     * @param msg
     * @param status
     * @return
     */
    public static RestApiResponse result(String msg, HttpStatus status) {
        RestApiResponse r = new RestApiResponse();
        boolean bl=status!=null & status.value()>=200 & status.value()<300;
        r.setStatus(status.value());
        r.setSuccess(bl);
       // r.setSuccess(true);
        r.setMessage(msg);
        r.setHost(getIp());
        r.setTimestamp(new Date());
        //r.setData(null);
        return r;
    }

    /**
     * 返回成功状态和数据
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestApiResponse<T> OK(T data) {
        return result(data, "调用成功!", HttpStatus.OK);
    }

    /**
     * 只返回提示信息
     * @param msg
     * @return
     */
    public static RestApiResponse OK(String msg) {
        return result(msg, HttpStatus.OK);
    }

    /**
     * 返回成功状态和数据
     *
     * @param data
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> RestApiResponse<T> OK(T data, String msg) {
        return result(data, msg, HttpStatus.OK);
    }

    /**
     * 返回警告信息
     *
     * @param msg
     * @param status
     * @param <T>
     * @return
     */
    public static <T> RestApiResponse<T> WARNING(String msg, HttpStatus status) {
        return result(null, msg, status);
    }

    /**
     * 返回异常信息
     *
     * @param javafreeExceptionType
     * @param errorMessage
     * @return
     */
    public static RestApiResponse ERROR(JavafreeExceptionType javafreeExceptionType, String errorMessage) {
        RestApiResponse r = new RestApiResponse();
        r.setStatus(javafreeExceptionType.getCode());
        r.setSuccess(false);
        //r.setSuccess(true);
        r.setHost(getIp());
        r.setMessage(errorMessage);
        r.setTimestamp(new Date());
       // r.setData(null);
        return r;
    }

    /**
     * 返回异常信息
     *
     * @param e
     * @return
     */
    public static RestApiResponse ERROR(JavafreeException e) {
        RestApiResponse r = new RestApiResponse();
        r.setStatus(e.getCode());
        r.setSuccess(false);
        //r.setSuccess(true);
        r.setHost(getIp());
        r.setMessage(e.getMessage());
        r.setTimestamp(new Date());
        //r.setData(null);
        return r;
    }

}
