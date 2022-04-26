package com.javafree.cloud.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/3/27 11:09
 */
@ApiModel(value = "分页结果对象", description = "显示分页的内容数据，及分页信息数据")
public class PageResult<T> implements Serializable {
  private static final long serialVersionUID = 307191652565452015L;

  @ApiModelProperty("条目数据对象列表")
  private  final List<T> content;
  @ApiModelProperty("总页数")
  private  final int totalPages;
  @ApiModelProperty("当前是否是最后一页")
  private  final boolean last;
  @ApiModelProperty("总条目数")
  private  final long totalElements;
  @ApiModelProperty("每页条目数")
  private  final int pageSize;
  @ApiModelProperty("当前页页码,此值加1才是当前真正页码")
  private  final int pageNumber;
  @ApiModelProperty("当前页行数")
  private  final int numberOfElements;
  @ApiModelProperty("当前是否是第一页")
  private  final boolean first;
  @ApiModelProperty("当前页记录是否为空")
  private  final boolean  empty;


  @ApiModelProperty("当前查询记录是否用了排序")
  private  final boolean  sorted;

  public PageResult(List<T> content, int totalPages, boolean last,
                    long totalElements, int pageSize, int pageNumber,
                    int numberOfElements,boolean first,boolean empty,boolean sorted){
    this.content=content;
    this.totalPages=totalPages;
    this.last=last;
    this.totalElements=totalElements;
    this.pageSize=pageSize;
    this.pageNumber=pageNumber;
    this.numberOfElements=numberOfElements;
    this.first=first;
    this.empty=empty;
    this.sorted=sorted;
  }


  public  static <T> PageResult<T> of(Page<T> page) {
    if(page!=null) {
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
    }else{
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
