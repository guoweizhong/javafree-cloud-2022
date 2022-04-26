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

//@RepositoryRestResource(path="role")
public interface RoleDao extends JpaRepository<Role, String> , JpaSpecificationExecutor<Role> {
}
