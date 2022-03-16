package com.javafree.cloud.common.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 对分页参数对象进行处理
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/1 12:30
 */

public class PageParamUtils {

    /**
     * 通过PageParam 参数，返回 Pageable
     * @param pageParam
     * @return
     */
    public static Pageable packagePageable(PageParam pageParam){

        Pageable pageable =null;

        if(pageParam.getSorts()!=null)
        {
            //有排序字段对象
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            //加入排序字段对象列表
            for (SortParam sort:pageParam.getSorts())
            {
                Sort.Order tmpOrder = new Sort.Order(Sort.Direction.valueOf(sort.getDirection()), sort.getProperty());
                orders.add(tmpOrder);
            }
            pageable = PageRequest.of(pageParam.getPage(), pageParam.getSize(), Sort.by(orders));
        }else {
            //无排序字段对象
            pageable =PageRequest.of(pageParam.getPage(), pageParam.getSize());
        }
        return pageable;
    }
}
