package com.javafree.cloud.admin.service;

import com.javafree.cloud.admin.entity.Dept;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageResult;

import java.util.List;

/**
 * @version V1.0
 * @Description: Dept Service 接口
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/15 11:20
 */

public interface DeptService {

    Dept saveDept(Dept dept);
    void deleteDeptByIds(List<String> ids);
    void deleteDept(String id);

    Dept getDeptById(String id);
    Dept getDeptByName(String deptName);

    List<Dept> getDeptsByParentId(String parentid);
    long getCountOfDept(String parentId);

    //取所有记录
    List<Dept> getAllDepts();

     PageResult<Dept> findDeptsByDeptBySpecification(Dept dept, PageParam pageParam);


    PageResult<Dept> findDeptsByDept(Dept Dept, PageParam pageParam);

    PageResult<Dept> findDeptsByDeptAny(Dept Dept, PageParam pageParam);
}
