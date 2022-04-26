package com.javafree.cloud.admin.dao;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/27 14:35
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@RepositoryRestResource(path = "dept")
public interface DeptDao extends JpaRepository<Dept, String>, JpaSpecificationExecutor<Dept> {
    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
     * clearAutomatically清除底层持久化上下文
     *
     * @param deptid
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Dept d where d.id = ?1")
    void deleteDeptById(String deptid);

    /**
     * 删除
     *
     * @param parentid
     */
    @Transactional
    @Modifying
    @Query("delete from Dept d where d.parent_id = ?1")
    void deleteDeptByParentId(String parentid);

    /**
     * 通过id列表，批量删除机构部门
     *
     * @param ids
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Dept d where d.id in (:ids) ")
    int deleteDeptByIds(@Param("ids") List<String> ids);
}
