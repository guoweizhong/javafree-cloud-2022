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
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="group")
public interface GroupDao extends JpaRepository<Group,String> , JpaSpecificationExecutor<Group> {

}
