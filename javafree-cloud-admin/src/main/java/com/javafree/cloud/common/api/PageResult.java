package com.javafree.cloud.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/3/27 11:09
 */
@Schema(name = "分页结果对象", description = "显示分页的内容数据，及分页信息数据")
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 307191652565452015L;

    @Schema(name = "content", description = "条目数据对象列表")
    private final List<T> content;
    @Schema(name = "totalPages", description = "总页数")
    private final int totalPages;
    @Schema(name = "last", description = "当前是否是最后一页")
    private final boolean last;
    @Schema(name = "totalElements", description = "总条目数")
    private final long totalElements;
    @Schema(name = "pageSize", description = "每页条目数")
    private final int pageSize;
    @Schema(name = "pageNumber", description = "当前页页码,此值加1才是当前真正页码")
    private final int pageNumber;
    @Schema(name = "numberOfElements", description = "当前页行数")
    private final int numberOfElements;
    @Schema(name = "first", description = "当前是否是第一页")
    private final boolean first;
    @Schema(name = "empty", description = "当前页记录是否为空")
    private final boolean empty;


    @Schema(name = "sorted", description = "当前查询记录是否用了排序")
    private final boolean sorted;

    public PageResult(List<T> content, int totalPages, boolean last,
                      long totalElements, int pageSize, int pageNumber,
                      int numberOfElements, boolean first, boolean empty, boolean sorted) {
        this.content = content;
        this.totalPages = totalPages;
        this.last = last;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.numberOfElements = numberOfElements;
        this.first = first;
        this.empty = empty;
        this.sorted = sorted;
    }


    public static <T> PageResult<T> of(Page<T> page) {
        if (page != null) {
            return new PageResult(page.getContent(),
                    page.getTotalPages(),
                    page.isLast(),
                    page.getTotalElements(),
                    page.getSize(),
                    page.getNumber(),
                    page.getNumberOfElements(),
                    page.isFirst(),
                    page.isEmpty(),
                    page.getSort().isSorted());
        } else {
            return null;
        }
    }

    public boolean isSorted() {
        return sorted;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isEmpty() {
        return empty;
    }


    public List<T> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }


}
