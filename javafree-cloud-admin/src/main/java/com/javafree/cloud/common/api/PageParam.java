package com.javafree.cloud.common.api;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(name = "分页查询参数对象", description = "用于前端传入分页查询参数")
public class PageParam implements Serializable {

    private static final long serialVersionUID = 5231391460048825825L;

    //页码
    @Schema(name = "currentPage", description = "当前页码，默认为1")
    int currentPage;
    //每页条目数
    @Schema(name = "pageSize", description = "每页条目数，默认20条")
    int pageSize;

    @Schema(name = "sorts", description = "排序条件")
    List<SortParam> sorts;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<SortParam> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortParam> sorts) {
        this.sorts = sorts;
    }

    public PageParam(){
        currentPage=1;
        pageSize=20;
    }
}
