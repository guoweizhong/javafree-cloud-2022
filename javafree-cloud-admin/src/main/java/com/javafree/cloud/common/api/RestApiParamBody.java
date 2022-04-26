package com.javafree.cloud.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 接口查询参数对象，分为dataParam：查询条件参数对象，
 * 和pageParam：查询分页参数对象
 * 示例数据格式：
 * {
 *   "dataParam": {
 *     "realname": "张三",
 *     "password": "passlsllsls",
 *     "Deptname": "zhsan"
 *   },
 *   "pageParam": {
 *     "page": 0,
 *     "size": 20,
 *     "sorts": [
 *       {
 *         "direction": "ASC",
 *         "property": "Deptname"
 *       },
 *       {
 *           "direction": "DESC",
 *           "property": "realname"
 *         }
 *     ]
 *   }
 * }
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/30 13:43
 */
@ApiModel(value = "接口查询参数对象", description = "接口查询参数对象，分为dataParam：查询条件参数对象，和pageParam：查询分页参数对象")
public class RestApiParamBody<T> implements Serializable {
    private static final long serialVersionUID = 1928441826295030501L;

    @ApiModelProperty("查询条件参数对象")
    private T dataParam;
    @ApiModelProperty("查询分页参数对象")
    private PageParam pageParam;

    public T getDataParam() {
        return dataParam;
    }

    public void setDataParam(T dataParam) {
        this.dataParam = dataParam;
    }

    public PageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(PageParam pageParam) {
        this.pageParam = pageParam;
    }

}
