package com.javafree.cloud.admin.service;

import com.javafree.cloud.admin.entity.Group;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageResult;

import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/5 13:49
 */

public interface GroupService {
    Group saveGroup(Group group);

    void deleteGroupByIds(List<String> ids);

    void deleteGroup(String id);

    Group getGroupById(String id);

    Group getGroupByName(String groupName);

    PageResult<Group> findGroupsByName(String name, PageParam pageParam);

    PageResult<Group> findGroupsByGroup(Group group, PageParam pageParam);

    PageResult<Group> findGroupsByGroupAny(Group group, PageParam pageParam);
}
