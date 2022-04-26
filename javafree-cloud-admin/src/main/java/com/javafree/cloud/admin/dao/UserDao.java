package com.javafree.cloud.admin.dao;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 17:17
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//@RepositoryRestResource(path="user")
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
     * clearAutomatically清除底层持久化上下文
     * @param id  用户ID
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from User u where u.id = ?1")
    void deleteUserById(String id);

    /**
     * 通过id列表，批量删除用户
     * @param ids
     * @return
     */
    @Modifying
    @Transactional
    @Query(value="delete from User u where u.id in (:ids) ")
    int deleteUserByIds(@Param("ids") List<String> ids);

    /**
     * 通过用户登录名查询
     * @param username
     * @return
     */
    @Query("select u from User u where u.username = ?1")
    User getUserByName(String username);









}
