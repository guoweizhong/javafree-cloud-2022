package com.javafree.cloud.admin.dao;

import com.javafree.cloud.admin.entity.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/11 13:43
 */

public interface IdentityDao extends JpaRepository<Identity, String>, JpaSpecificationExecutor<Identity> {
    /**
     * 通过主键删除
     *
     * @param id 主键
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Identity o where o.id = ?1")
    void deleteIdentityById(String id);

    /**
     * 通过多个主键删除
     *
     * @param ids 主键列表
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Identity o where o.id in (:ids) ")
    int deleteIdentityByIds(@Param("ids") List<String> ids);

    /**
     * 通过角色主键删除
     *
     * @param id 角色主键
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Identity o where o.roleId = ?1")
    void deleteIdentityByRoleId(String id);

    /**
     * 通过多个角色主键删除
     *
     * @param ids 角色主键列表
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Identity o where o.roleId in (:ids) ")
    int deleteIdentityByRoleIds(@Param("ids") List<String> ids);


    /**
     * 通过用户主键删除
     *
     * @param id 用户主键
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Identity o where o.userId = ?1")
    void deleteIdentityByUserId(String id);

    /**
     * 通过多个用户主键删除
     *
     * @param ids 用户主键列表
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Identity o where o.userId in (:ids) ")
    int deleteIdentityByUserIds(@Param("ids") List<String> ids);

    /**
     * 通过机构主键删除
     *
     * @param id 机构主键
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Identity o where o.deptId = ?1")
    void deleteIdentityByDeptId(String id);

    /**
     * 通过多个机构主键删除
     *
     * @param ids 机构主键列表
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Identity o where o.deptId in (:ids) ")
    int deleteIdentityByDeptIds(@Param("ids") List<String> ids);

    /**
     * 按机构ID统计机构下的关联数量
     *
     * @return
     */
    @Query(value = "SELECT o.deptId, COUNT(o.deptId) FROM Identity AS o GROUP BY o.deptId")
    List<Object[]> countDeptsByDeptId();

    @Query("select count(t) from  Identity t where t.userId = :#{#identity.userId} and t.roleId = :#{#identity.roleId} and t.deptId = :#{#identity.deptId}")
    Integer findIdentityCountByRelID(@Param("identity") Identity identity);

    /**
     * 按角色ID统计角色下的关联数量
     * @return
     */
    @Query(value = "SELECT o.roleId, COUNT(o.roleId) FROM Identity AS o GROUP BY o.roleId")
    List<Object[]> countRolesByRoleId();

}
