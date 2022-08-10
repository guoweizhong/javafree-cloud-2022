package com.javafree.cloud.admin.service.impl;

import com.javafree.cloud.admin.dao.RoleDao;
import com.javafree.cloud.admin.entity.Role;
import com.javafree.cloud.admin.service.RoleService;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageParamUtils;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.utils.JavaFreeBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/6/7 15:47
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public void deleteRoleByIds(List<String> ids) {
        roleDao.deleteRoleByIds(ids);
    }

    @Override
    public void deleteRole(String id) {
        roleDao.deleteRoleById(id);
    }

    @Override
    public Role getRoleById(String id) {
        return roleDao.findById(id).orElse(null);
    }

    @Override
    public Role getRoleByCode(String code) {
        return roleDao.getRoleByCode(code);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public List<Object[]> countRolesByType() {
        return roleDao.countRolesByType();
    }

    @Override
    public List<Role> getRolesListByRole(Role role) {
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matching()
                //全部模糊查询，即%{role}%
                .withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略为空值字段
                .withIgnoreNullValues();
        Example<Role> example = Example.of(role, matcher);
        return roleDao.findAll(example);
    }

    @Override
    public PageResult<Role> findRolesByName(String name, PageParam pageParam) {
        Role role = new Role();
        role.setRoleName(name);
        return findRolesByRole(role, pageParam);
    }

    @Override
    public PageResult<Role> findRolesByRole(Role role, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matching()
                //全部模糊查询，即%{role}%
                .withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略为空值字段
                .withIgnoreNullValues();
        Example<Role> example = Example.of(role, matcher);
        return PageResult.of(roleDao.findAll(example, pageable));
    }

    /**
     * 通过角色对象为条件返回角色分页列表数据,条件关系为or
     * @param Role
     * @param pageParam
     * @return
     */
    @Override
    public PageResult<Role> findRolesByRoleAny(Role Role, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是or
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略为空值字段
                .withIgnoreNullValues();
        Example<Role> example = Example.of(Role, matcher);
        return PageResult.of(roleDao.findAll(example, pageable));
    }

    /**
     * 注意更新和删除是需要加事务的， 并且要加上 Modifying的注解
     * clearAutomatically清除底层持久化上下文
     * @param role
     * @return
     */

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    public Role saveRole(Role role) {
        Assert.notNull(role, "Role 对象不能为空.");
        if (StringUtils.hasText(role.getId())) {
            Role tempRole = roleDao.findById(role.getId()).orElse(null);
            Assert.notNull(tempRole, "Role ID：" + role.getId() + " 数据库中没有找到.");
            //将传入的Role对象值copy到tempRole对象中，并忽略Role对象为空的属性
            BeanUtils.copyProperties(role, tempRole, JavaFreeBeanUtils.getNullPropertyNames(role));
            return roleDao.save(tempRole);
        }else {
            return roleDao.save(role);
        }

    }
}
