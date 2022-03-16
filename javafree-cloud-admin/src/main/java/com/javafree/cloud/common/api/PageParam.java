package com.javafree.cloud.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 用于前端传入分页查询参数
 * @version V1.0
 * @Description:  从调用端传入分页参数信息
 *          页面传参数据格式为：
 *          {
 *            "page" : 0,
 *            "size" : 20,
 *            "sorts" : [
 *                  {
 *                   "property" : "name",
 *                   "direction" : "ASC"
 *                 },
 *                 {
 *                   "property" : "age",
 *                   "direction" : "DESC"
 *                }
 *                ]
 *            }
 * @Author gwz  gwz126@126.com
 * @Date 2021/11/30 9:10
 */
@ApiModel(value = "分页查询参数对象", description = "用于前端传入分页查询参数")
public class PageParam implements Serializable {

    private static final long serialVersionUID = 5231391460048825825L;

    //页码
    @ApiModelProperty("页码，默认为0")
    int page;
    //每页条目数
    @ApiModelProperty("每页条目数，默认20条")
    int size;

    @ApiModelProperty("排序条件")
    List<SortParam> sorts;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<SortParam> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortParam> sorts) {
        this.sorts = sorts;
    }

    public PageParam(){
        page=0;
        size=20;
    }



}
