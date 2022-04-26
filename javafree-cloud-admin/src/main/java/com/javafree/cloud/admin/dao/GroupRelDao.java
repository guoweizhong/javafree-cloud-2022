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

//@RepositoryRestResource(path="group_rel")
public interface GroupRelDao extends JpaRepository<GroupRel, String>, JpaSpecificationExecutor<GroupRel> {

}
