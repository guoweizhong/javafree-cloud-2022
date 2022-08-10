package com.javafree.cloud.admin.service;

import com.javafree.cloud.admin.entity.Identity;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageResult;

import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/11 14:09
 */

public interface IdentityService {

    /**
     * 通过主键获得对象
     * @param id
     * @return
     */
    Identity getIdentityById(String id);

    PageResult<Identity> findIdentitysByIdentity(Identity identity, PageParam pageParam);

    PageResult<Identity> findIdentitysByIdentityAny(Identity identity, PageParam pageParam);

    PageResult<Identity> findIdentitysByDeptID(Identity identity, PageParam pageParam);

    /**
     * 新增和修改identity对象
     * @param identity
     * @return
     */
    Identity saveIdentity(Identity identity);

    /**
     * 通过主键删除
     *
     * @param id 主键
     */
    void deleteIdentityById(String id);

    /**
     * 通过多个主键删除
     *
     * @param ids 主键列表
     * @return
     */
    void deleteIdentityByIds(List<String> ids);

    /**
     * 通过角色主键删除
     *
     * @param id 角色主键
     */
    void deleteIdentityByRoleId(String id);

    /**
     * 通过多个角色主键删除
     *
     * @param ids 角色主键列表
     * @return
     */
    void deleteIdentityByRoleIds(List<String> ids);


    /**
     * 通过用户主键删除
     *
     * @param id 用户主键
     */
    void deleteIdentityByUserId(String id);

    /**
     * 通过多个用户主键删除
     *
     * @param ids 用户主键列表
     * @return
     */
    void deleteIdentityByUserIds(List<String> ids);

    /**
     * 通过机构主键删除
     *
     * @param id 机构主键
     */
    void deleteIdentityByDeptId(String id);

    /**
     * 通过多个机构主键删除
     *
     * @param ids 机构主键列表
     * @return
     */
    void deleteIdentityByDeptIds(List<String> ids);


    /**
     * 按机构ID统计机构下的关联数量
     * @return
     */
    List<Object[]> countDeptsByDeptId();

    /**
     * 按角色ID统计角色下的关联数量
     * @return
     */
    List<Object[]> countRolesByRoleId();

}