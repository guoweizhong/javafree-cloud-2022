package com.javafree.cloud.admin.dao;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 17:17
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.GroupRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupRelDao extends JpaRepository<GroupRel, String>, JpaSpecificationExecutor<GroupRel> {
    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
     * clearAutomatically清除底层持久化上下文
     * @param id 组与成员关系ID
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from GroupRel o where o.id = ?1")
    void deleteGroupRelById(String id);

    /**
     * 通过id列表，批量删除组与成员关系
     *
     * @param ids
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from GroupRel o where o.id in (:ids) ")
    int deleteGroupRelByIds(@Param("ids") List<String> ids);

    /**
     * 通过组名查询
     *
     * @param groupName
     * @return
     */
    @Query("select o from GroupRel o where o.groupName = ?1")
    List<GroupRel> getGroupRelByGroupName(String groupName);


    /**
     * 删除关系，通过组ID
     * @param id 组ID
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from GroupRel o where o.groupId = ?1")
    void deleteGroupRelByGroupId(String id);

    /**
     * 通过组ID 删除关系
     *
     * @param ids 组id列表
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from GroupRel o where o.groupId in (:ids) ")
    int deleteGroupRelByGroupIds(@Param("ids") List<String> ids);


    /**
     * 通过组成员对象名查询
     *
     * @param objName
     * @return
     */
    @Query("select o from GroupRel o where o.objName = ?1")
    List<GroupRel> getGroupRelByObjName(String objName);

    /**
     * 删除关系，通过关联对象
     * @param id 组ID
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from GroupRel o where o.objId = ?1")
    void deleteGroupRelByObjId(String id);

    /**
     * 通过有关联对象ID 删除关系
     *
     * @param ids 关联对象id列表
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from GroupRel o where o.objId in (:ids) ")
    int deleteGroupRelByObjIds(@Param("ids") List<String> ids);
}
