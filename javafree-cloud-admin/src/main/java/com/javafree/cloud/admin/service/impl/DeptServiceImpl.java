package com.javafree.cloud.admin.service.impl;

import com.javafree.cloud.admin.dao.DeptDao;
import com.javafree.cloud.admin.entity.Dept;
import com.javafree.cloud.admin.service.DeptService;
import com.javafree.cloud.common.api.PageParam;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/15 11:21
 */
@Service
public class DeptServiceImpl implements DeptService {

    DeptDao deptDao;

    @Override
    public Dept saveDept(Dept dept) {
        return null;
    }

    @Override
    public void deleteDeptByIds(List<String> ids) {

    }

    @Override
    public void deleteDept(String id) {

    }

    @Override
    public Dept getDeptById(String id) {
        return null;
    }

    @Override
    public Dept getDeptByName(String deptName) {
        return null;
    }

    @Override
    public Page<Dept> findDeptsByDept(Dept Dept, PageParam pageParam) {
        return null;
    }

    @Override
    public Page<Dept> findDeptsByDeptAny(Dept Dept, PageParam pageParam) {
        return null;
    }
}
