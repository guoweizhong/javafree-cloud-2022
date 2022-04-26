package com.javafree.cloud.admin.service;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 17:34
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.User;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageResult;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    void deleteUserByIds(List<String> ids);
    void deleteUser(String id);

    User getUserById(String id);
    User getUserByName(String userName);

    PageResult<User> findUsersByRealname(String realname, PageParam pageParam);

    PageResult<User> findUsersByUser(User user, PageParam pageParam);

    PageResult<User> findUsersByUserAny(User user, PageParam pageParam);
}
