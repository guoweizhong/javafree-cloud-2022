package com.javafree.cloud.admin.dao;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/31 14:06
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@RepositoryRestResource(path="role")
public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
     * clearAutomatically清除底层持久化上下文
     *
     * @param id 角色ID
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Role t where t.id = ?1")
    void deleteRoleById(String id);

    /**
     * 通过id列表，批量删除角色
     *
     * @param ids
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Role t where t.id in (:ids) ")
    int deleteRoleByIds(@Param("ids") List<String> ids);

    /**
     * 通过角色名查询
     *
     * @param code
     * @return
     */
    @Query("select t from Role t where t.roleCode = ?1")
    Role getRoleByCode(String code);

    @Query("select t from Role t where t.roleName = ?1")
    Role getRoleByName(String roleName);

    /**
     * 获得按角色类型进行统计的统计结果列表
     * @return
     */
    @Query(value = "SELECT c.roleType, COUNT(c.roleType) FROM Role AS c GROUP BY c.roleType order by c.roleType ")
    List<Object[]> countRolesByType();


}
