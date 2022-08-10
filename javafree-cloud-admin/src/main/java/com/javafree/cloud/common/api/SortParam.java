package com.javafree.cloud.common.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 用于前端传入分页排序参数
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/30 9:56
 */
@Schema(name = "SortParam", description = "分页排序对象，用于前端传入分页排序参数")
public class SortParam implements Serializable {

    private static final long serialVersionUID = 3061791274834419230L;


    //排序字段名称
    @Schema(name = "property", description = "排序字段名称")
    private  String property;

    //排序方式 值只能是“ASC”升序 或 ”DESC“降序
    @Schema(name = "direction", description = "排序方式 值只能是“ASC”升序 或 ”DESC“降序")
    private  String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
