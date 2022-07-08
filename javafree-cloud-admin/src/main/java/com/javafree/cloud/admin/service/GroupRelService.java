package com.javafree.cloud.admin.service;

import com.javafree.cloud.admin.entity.GroupRel;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageResult;

import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/7 13:17
 */

public interface GroupRelService {
    /**
     * 获得组成员关系对象
     * @param id 对象ID
     * @return
     */
    GroupRel getGroupRelById(String id);
    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modify的注解
     * clearAutomatically清除底层持久化上下文
     * @param id 组与成员关系ID
     */
    void deleteGroupRelById(String id);

    /**
     * 通过id列表，批量删除组与成员关系
     *
     * @param ids
     * @return
     */
    void deleteGroupRelByIds( List<String> ids);

    /**
     * 通过组名查询
     *
     * @param groupName
     * @return
     */
    List<GroupRel> getGroupRelByGroupName(String groupName);


    /**
     * 删除关系，通过组ID
     * @param id 组ID
     */
    void deleteGroupRelByGroupId(String id);

    /**
     * 通过组ID 删除关系
     *
     * @param ids 组id列表
     * @return
     */
    void deleteGroupRelByGroupIds(List<String> ids);


    /**
     * 通过组成员对象名查询
     *
     * @param objName
     * @return
     */
    List<GroupRel> getGroupRelByObjName(String objName);

    /**
     * 删除关系，通过关联对象
     * @param id 组ID
     */
    void deleteGroupRelByObjId(String id);

    /**
     * 通过有关联对象ID 删除关系
     *
     * @param ids 关联对象id列表
     * @return
     */
    void deleteGroupRelByObjIds(List<String> ids);

    /**
     * 保存或修改GroupRel
     * @param groupRel
     * @return
     */
    GroupRel saveGroupRel(GroupRel groupRel);

    PageResult<GroupRel> findGroupRelsByGroupRel(GroupRel groupRel, PageParam pageParam);

    PageResult<GroupRel> findGroupRelsByGroupRelAny(GroupRel groupRel, PageParam pageParam);
}
