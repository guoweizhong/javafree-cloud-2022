package com.javafree.cloud.admin.service;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 17:34
 * @version V1.0
 */

import com.javafree.cloud.admin.entity.User;
import com.javafree.cloud.common.api.PageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User saveUser(User User);
    void deleteUserByIds(List<String> ids);
    void deleteUser(String id);

    User getUserById(String id);
    User getUserByName(String userName);

    Page<User> findUsersByRealname(String realname, PageParam pageParam);

    Page<User> findUsersByUser(User user, PageParam pageParam);

    Page<User> findUsersByUserAny(User user, PageParam pageParam);
}
