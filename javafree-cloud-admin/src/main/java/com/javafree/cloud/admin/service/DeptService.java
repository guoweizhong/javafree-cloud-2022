package com.javafree.cloud.admin.service;

import com.javafree.cloud.admin.entity.Dept;
import com.javafree.cloud.common.api.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/15 11:20
 */

public interface DeptService {

    Dept saveDept(Dept dept);
    void deleteDeptByIds(List<String> ids);
    void deleteDept(String id);

    Dept getDeptById(String id);
    Dept getDeptByName(String deptName);


    Page<Dept> findDeptsByDept(Dept Dept, PageParam pageParam);

    Page<Dept> findDeptsByDeptAny(Dept Dept, PageParam pageParam);
}
