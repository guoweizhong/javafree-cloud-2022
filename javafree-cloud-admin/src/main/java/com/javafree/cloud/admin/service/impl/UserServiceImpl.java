package com.javafree.cloud.admin.service.impl;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 17:40
 * @version V1.0
 */

import com.javafree.cloud.admin.dao.UserDao;
import com.javafree.cloud.admin.entity.User;
import com.javafree.cloud.admin.service.UserService;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageParamUtils;
import com.javafree.cloud.common.utils.JavaFreeBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public void deleteUserByIds(List<String> ids) {
        userDao.deleteUserByIds(ids);
    }

    @Override
    public void deleteUser(String Userid) {
        userDao.deleteUserById(Userid);
    }

    @Override
    public User getUserById(String Userid) {
        return userDao.findById(Userid).orElse(null);
    }

    @Override
    public User getUserByName(String UserName) {
        return userDao.getUserByName(UserName);
    }

    @Override
    public Page<User> findUsersByRealname(String realname, PageParam pageParam) {
        User User = new User();
        User.setRealname(realname);
        return findUsersByUser(User, pageParam);
    }

    /**
     * 通过用户对象为条件返回用户分页列表数据,条件关系为and
     *
     * @param user      条件参数
     * @param pageParam 分页参数 参数格式：
     *                  {
     *                  "page": 0,
     *                  "size": 20,
     *                  "sorts": [
     *                  {
     *                  "direction": "ASC",
     *                  "property": "Username"
     *                  }
     *                  ]
     *                  }
     * @return
     */
    @Override
    public Page<User> findUsersByUser(User user, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matching()
                //忽略为空值字段
                .withIgnoreNullValues()
                //全部模糊查询，即%{username}%
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("realname", ExampleMatcher.GenericPropertyMatchers.contains())
                // 忽略字段，即不管password是什么值都不加入查询条件
                .withIgnorePaths("password");

        Example<User> example = Example.of(user, matcher);

        return userDao.findAll(example, pageable);
    }

    /**
     * 通过用户对象为条件返回用户分页列表数据,条件关系为or
     *
     * @param User
     * @param pageParam
     * @return
     */
    @Override
    public Page<User> findUsersByUserAny(User User, PageParam pageParam) {

        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是or
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                //忽略为空值字段
                .withIgnoreNullValues()
                //全部模糊查询，即%{Username}%
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("realname", ExampleMatcher.GenericPropertyMatchers.contains())
                // 忽略字段，即不管password是什么值都不加入查询条件
                .withIgnorePaths("password");
        Example<User> example = Example.of(User, matcher);

        return userDao.findAll(example, pageable);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class)
    @Modifying
    public User saveUser(User user) {
        Assert.notNull(user, "User 对象不能为空.");
        User tempUser = new User();
        if (StringUtils.hasText(user.getId())) {
            tempUser = userDao.findById(user.getId()).orElse(null);
            Assert.notNull(tempUser, "User ID：" + user.getId() + " 数据库中没有找到.");
        }
        //将传入的User对象值copy到tempUser对象中，并忽略User对象为空的属性
        BeanUtils.copyProperties(user, tempUser, JavaFreeBeanUtils.getNullPropertyNames(user));

        return userDao.save(tempUser);
    }

}
