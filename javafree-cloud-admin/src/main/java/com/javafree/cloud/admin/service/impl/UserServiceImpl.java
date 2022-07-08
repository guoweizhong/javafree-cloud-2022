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
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.utils.JavaFreeBeanUtils;
import com.javafree.cloud.common.utils.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
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
    public void deleteUser(String id) {
        userDao.deleteUserById(id);
    }

    @Override
    public User getUserById(String id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public PageResult<User> findUsersByRealname(String realname, PageParam pageParam) {
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
    public PageResult<User> findUsersByUser(User user, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matching()
                //全部模糊查询，即%{username}%
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("realname", ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略为空值字段
                .withIgnoreNullValues()
                // 忽略字段，即不管password是什么值都不加入查询条件
                .withIgnorePaths("password");

        Example<User> example = Example.of(user, matcher);

        return PageResult.of(userDao.findAll(example, pageable));
    }

    /**
     * 通过用户对象为条件返回用户分页列表数据,条件关系为or
     *
     * @param User
     * @param pageParam
     * @return
     */
    @Override
    public PageResult<User> findUsersByUserAny(User User, PageParam pageParam) {

        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是or
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                //忽略为空值字段
                .withIgnoreNullValues()
                //全部模糊查询，即%{Username}%
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("realname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("workNo", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains())
                // 忽略字段，即不管password是什么值都不加入查询条件
                .withIgnorePaths("password");
        Example<User> example = Example.of(User, matcher);

        return PageResult.of(userDao.findAll(example, pageable));
    }

    /**
     * 注意更新和删除是需要加事务的， 并且要加上 Modifying的注解
     * clearAutomatically清除底层持久化上下文
     *
     * @param user
     * @return
     */

    @Transactional
    @Modifying
    public User saveUser(User user) {
        Assert.notNull(user, "User 对象不能为空.");

        if (StringUtils.hasText(user.getPassword())){
            //如果页面传的密码不为空，则要进行加密后保存
            String encryption=PasswordUtils.encryption(user.getPassword());
            user.setPassword(encryption);
        }
        //有ID为修改
        if (StringUtils.hasText(user.getId())) {
            User tempUser =  userDao.findById(user.getId()).orElse(null);
            Assert.notNull(tempUser, "User ID：" + user.getId() + " 数据库中没有找到.");
            //将传入的User对象值copy到tempUser对象中，并忽略User对象为空的属性
            BeanUtils.copyProperties(user, tempUser, JavaFreeBeanUtils.getNullPropertyNames(user));
            return userDao.save(tempUser);
        }else{
            //新增时判断是否已经有用户
            Assert.isNull(userDao.getUserByName(user.getUsername()),"保存失败，该用户名已存在！");
        }

        return userDao.save(user);
    }

}
