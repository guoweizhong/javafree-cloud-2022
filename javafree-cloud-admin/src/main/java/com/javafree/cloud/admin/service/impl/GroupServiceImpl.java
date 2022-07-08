package com.javafree.cloud.admin.service.impl;

import com.javafree.cloud.admin.dao.GroupDao;
import com.javafree.cloud.admin.entity.Group;
import com.javafree.cloud.admin.service.GroupService;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageParamUtils;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.utils.JavaFreeBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/5 13:52
 */
@Service
public class GroupServiceImpl implements GroupService {
  @Autowired
  GroupDao groupDao;
  @Override
  public Group saveGroup(Group group) {
    Assert.notNull(group, "Group 对象不能为空.");
    //更新时只更新修改的内容
    if (StringUtils.hasText(group.getId())) {
      Group tempObj = groupDao.findById(group.getId()).orElse(null);
      Assert.notNull(tempObj, "Group ID：" + group.getId() + " 数据库中没有找到.");
      //将传入的Group对象值copy到tempObj对象中，并忽略Group对象为空的属性
      BeanUtils.copyProperties(group, tempObj, JavaFreeBeanUtils.getNullPropertyNames(group));
      return groupDao.save(tempObj);
    }else {
      //新增
      return groupDao.save(group);
    }
  }

  @Override
  public void deleteGroupByIds(List<String> ids) {
    groupDao.deleteGroupByIds(ids);
  }

  @Override
  public void deleteGroup(String id) {
    groupDao.deleteGroupById(id);
  }

  @Override
  public Group getGroupById(String id) {
    return groupDao.findById(id).orElse(null);
  }

  @Override
  public Group getGroupByName(String groupName) {
    return groupDao.getGroupByName(groupName);
  }

  @Override
  public PageResult<Group> findGroupsByName(String name, PageParam pageParam) {
    Group group=new Group();
    group.setGroupName(name);
    return findGroupsByGroup(group,pageParam);
  }

  @Override
  public PageResult<Group> findGroupsByGroup(Group group, PageParam pageParam) {
    //通过pageparam 返回Pageable
    Pageable pageable = PageParamUtils.packagePageable(pageParam);
    //条件间的关系是and
    ExampleMatcher matcher = ExampleMatcher.matching()
            //全部模糊查询，即%{username}%
            .withMatcher("groupName", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains())
            //忽略为空值字段
            .withIgnoreNullValues();
    Example<Group> example = Example.of(group, matcher);
    return PageResult.of(groupDao.findAll(example, pageable));
  }

  @Override
  public PageResult<Group> findGroupsByGroupAny(Group group, PageParam pageParam) {
    //通过pageparam 返回Pageable
    Pageable pageable = PageParamUtils.packagePageable(pageParam);
    //条件间的关系是and
    ExampleMatcher matcher = ExampleMatcher.matchingAny()
            //全部模糊查询，即%{username}%
            .withMatcher("groupName", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains())
            //忽略为空值字段
            .withIgnoreNullValues();
    Example<Group> example = Example.of(group, matcher);
    return PageResult.of(groupDao.findAll(example, pageable));
  }
}
