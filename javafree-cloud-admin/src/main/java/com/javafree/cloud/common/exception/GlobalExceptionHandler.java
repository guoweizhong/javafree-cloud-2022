package com.javafree.cloud.common.exception;

import com.javafree.cloud.common.api.RestApiResponse;
import com.javafree.cloud.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局统一异常处理，为前端用户返回友好异常提示信息
 * 遇到全局异常处理不生效问题，原因是工程扫描包没有扫到，在Application 启动类加上了此类所在包目录，问题解决
 *
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/1 14:21
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 平台自定义异常拦截
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = JavafreeException.class)
    public RestApiResponse handleJavafreeException(JavafreeException e) {
        log.error("系统发生异常：{}", getStackTrace(e));
        return RestApiResponse.ERROR(e).setData(e.getMessage());
    }

    /**
     * 数据库访问相关异常拦截
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({SQLSyntaxErrorException.class,
            java.sql.SQLException.class,
            org.springframework.dao.InvalidDataAccessResourceUsageException.class})
    public RestApiResponse handleSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        log.error("数据库访问SQL异常：{}", getStackTrace(e));
        return RestApiResponse.ERROR(new JavafreeException(JavafreeExceptionType.SERVER_ERROR, "数据库访问SQL异常")).setData(e.getMessage());
    }

    /**
     * 用户提前数据格式校验异常拦截
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestApiResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        //输出异常信息
        log.error("用户提交数据字段值格式错误：校验信息为{} : 异常堆栈信息为：{}", JsonUtils.getJsonStringFromObject(errors),getStackTrace(ex));
        return RestApiResponse.ERROR(JavafreeExceptionType.CLIENT_ERROR,"用户提交数据字段值格式错误!").setData(ex.getMessage());
    }

    /**
     * 兜底异常拦截，以上方法异常之外的异常拦截
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public RestApiResponse handleRunTimeException(RuntimeException e) {
        //输出异常信息
        log.error("系统发生未知异常：{}", getStackTrace(e));
        return RestApiResponse.ERROR(new JavafreeException(JavafreeExceptionType.OTHER_ERROR)).setData(e.getMessage());
    }

    /**
     * 未知异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public RestApiResponse handleIllegalArgumentException(Exception e) {
        log.warn("未知错误：{0}", e);
        return RestApiResponse.ERROR(new JavafreeException(JavafreeExceptionType.OTHER_ERROR)).setData(e.getMessage());
    }

    /**
     * 获得异常堆栈信息
     *
     * @param throwable
     * @return
     */
    private String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
