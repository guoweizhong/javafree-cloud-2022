package com.javafree.cloud.admin.dao;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/27 14:46
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.Identities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="identities")
public interface IdentitiesDao extends JpaRepository<Identities, String>, JpaSpecificationExecutor<Identities> {
}
