package com.javafree.cloud.admin.service;

import com.javafree.cloud.admin.entity.Role;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageResult;

import java.util.List;
/**
 * @version V1.0
 * @Description: Role Service 接口
 * @Author gwz  gwz126@126.com
 * @Date 2022/6/7 15:17
 */


public interface RoleService {
    Role saveRole(Role role);

    void deleteRoleByIds(List<String> ids);

    void deleteRole(String id);

    Role getRoleById(String id);

    Role getRoleByCode(String code);
    Role getRoleByName(String roleName);

    List<Object[]> countRolesByType();

    PageResult<Role> findRolesByName(String name, PageParam pageParam);

    PageResult<Role> findRolesByRole(Role role, PageParam pageParam);

    PageResult<Role> findRolesByRoleAny(Role role, PageParam pageParam);
}
