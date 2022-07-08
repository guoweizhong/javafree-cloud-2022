package com.javafree.cloud.common.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.ArrayList;
import java.util.List;

/**
 * 对分页参数对象进行处理
 *
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/1 12:30
 */

public class PageParamUtils {

    /**
     * 通过PageParam 参数，返回 Pageable
     *
     * @param pageParam
     * @return
     */
    public static Pageable packagePageable(PageParam pageParam) {

        Pageable pageable = null;

        if (pageParam.getSorts() != null) {
            //有排序字段对象
            List<JpaSort.Order> orders = new ArrayList<JpaSort.Order>();
            //加入排序字段对象列表
            for (SortParam sort : pageParam.getSorts()) {
                //忽略desc和asc大小写
                String type = sort.getDirection().toUpperCase();
                switch (type) {
                    case "ASCEND":
                        type = "ASC";
                        break;
                    case "DESCEND":
                        type = "DESC";
                        break;
                    case "ASC":
                        type = "ASC";
                        break;
                    case "DESC":
                        type = "DESC";
                        break;
                    default:
                        type = "ASC";
                        break;
                }
                Sort.Order tmpOrder = new Sort.Order(Sort.Direction.valueOf(type), sort.getProperty());
                //JpaSort.Order tmpOrder=  JpaSort.unsafe(Sort.Direction.valueOf(type), sort.getProperty()).getOrderFor(sort.getProperty());
                orders.add(tmpOrder);
            }

            //从业务传来的页码值，在数据库层则要减1，比如第一页页码是1，数据库分页，第一页是0，所以这里要减1
            pageable = PageRequest.of(pageParam.getCurrentPage() - 1, pageParam.getPageSize(), Sort.by(orders));
        } else {
            //无排序字段对象
            pageable = PageRequest.of(pageParam.getCurrentPage() - 1, pageParam.getPageSize());
        }
        return pageable;
    }
}
