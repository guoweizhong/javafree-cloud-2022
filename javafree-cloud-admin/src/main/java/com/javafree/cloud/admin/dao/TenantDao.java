package com.javafree.cloud.admin.dao;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 17:17
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@RepositoryRestResource(path="tenant")
//@Repository
public interface TenantDao extends JpaRepository<Tenant, String>, JpaSpecificationExecutor<Tenant> {
    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
     * clearAutomatically清除底层持久化上下文
     *
     * @param id 租户ID
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Tenant t where t.id = ?1")
    void deleteTenantById(String id);

    /**
     * 通过id列表，批量删除租户
     *
     * @param ids
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "delete from Tenant t where t.id in (:ids) ")
    int deleteTenantByIds(@Param("ids") List<String> ids);

    /**
     * 通过租户名查询
     *
     * @param code
     * @return
     */
    @Query("select t from Tenant t where t.tenantCode = ?1")
    Tenant getTenantByCode(String code);
}
