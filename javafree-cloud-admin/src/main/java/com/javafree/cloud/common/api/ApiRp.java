package com.javafree.cloud.common.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version V1.0
 * @Description: 在 Spring Boot 中使用 ResponseEntity 自定义 JSON 响应
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/23 18:05
 */
@Deprecated
public class ApiRp {
    /**
     *  通用返回数据封装
     * @param message  提示信息
     * @param status   状态编码
     * @param responseObj 数据
     * @return
     */
    public static ResponseEntity<Object> result( Object responseObj,String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        map.put("success",200==status.value()?true:false);
        map.put("message", message);
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("time",dateformat.format(new Date()));
        map.put("data", responseObj);
        return new ResponseEntity<Object>(map,status);
    }



    /**
     *  返回成功状态和数据
     * @param responseObj 数据
     * @return
     */
    public static ResponseEntity<Object> OK( Object responseObj) {
        return result(responseObj,"调用成功!",HttpStatus.OK);
    }

    /**
     *  返回成功状态和数据
     * @param responseObj 接口数据
     * @param message  提示信息
     * @return
     */
    public static ResponseEntity<Object> OK( Object responseObj,String message) {
        return result(responseObj,message,HttpStatus.OK);
    }

    /**
     * 返回500 错误状态信息
     * @return
     */
    public static ResponseEntity<Object> ERROR() {
        return ERROR("发生内部错误[500],调用失败！",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     *
     *  返回失败状态和提示信息
     * @param message 返回信息
     * @return
     */
    public static ResponseEntity<Object> ERROR(String message,HttpStatus status) {
        return result(null,message,status);
    }

    public static ResponseEntity<Object> WARNING(String message,HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        map.put("success",200==status.value()?true:false);
        map.put("message", message);
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("time",dateformat.format(new Date()));
        map.put("data", null);
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }



}
