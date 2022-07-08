package com.javafree.cloud.admin.dao;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 17:17
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GroupDao extends JpaRepository<Group,String> , JpaSpecificationExecutor<Group> {
    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
     * clearAutomatically清除底层持久化上下文
     * @param id 用户组ID
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Group u where u.id = ?1")
    void deleteGroupById(String id);

    /**
     * 通过id列表，批量删除用户组
     *
     * @param ids
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Group u where u.id in (:ids) ")
    int deleteGroupByIds(@Param("ids") List<String> ids);

    /**
     * 通过用户组登录名查询
     *
     * @param name
     * @return
     */
    @Query("select u from Group u where u.groupName = ?1")
    Group getGroupByName(String name);
}
