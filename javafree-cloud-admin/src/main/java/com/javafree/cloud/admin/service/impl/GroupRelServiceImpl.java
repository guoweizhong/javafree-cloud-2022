package com.javafree.cloud.admin.service.impl;

import com.javafree.cloud.admin.dao.GroupRelDao;
import com.javafree.cloud.admin.entity.GroupRel;
import com.javafree.cloud.admin.service.GroupRelService;
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
 * @Date 2022/7/7 13:41
 */
@Service
public class GroupRelServiceImpl implements GroupRelService {
  @Autowired
  GroupRelDao groupRelDao;

  @Override
  public void deleteGroupRelById(String id) {
    groupRelDao.deleteGroupRelById(id);
  }

  @Override
  public void deleteGroupRelByIds(List<String> ids) {
    groupRelDao.deleteGroupRelByIds(ids);
  }

  @Override
  public List<GroupRel> getGroupRelByGroupName(String groupName) {
    return groupRelDao.getGroupRelByGroupName(groupName);
  }

  @Override
  public void deleteGroupRelByGroupId(String id) {
    groupRelDao.deleteGroupRelByGroupId(id);
  }

  @Override
  public void deleteGroupRelByGroupIds(List<String> ids) {
    groupRelDao.deleteGroupRelByGroupIds(ids);
  }

  @Override
  public List<GroupRel> getGroupRelByObjName(String objName) {
    return groupRelDao.getGroupRelByObjName(objName);
  }

  @Override
  public void deleteGroupRelByObjId(String id) {
    groupRelDao.deleteGroupRelByObjId(id);
  }

  @Override
  public void deleteGroupRelByObjIds(List<String> ids) {
    groupRelDao.deleteGroupRelByObjIds(ids);
  }
  @Override
  public GroupRel getGroupRelById(String id) {
    return groupRelDao.findById(id).orElse(null);
  }
  @Override
  public GroupRel saveGroupRel(GroupRel groupRel) {
    Assert.notNull(groupRel, "GroupRel 对象不能为空.");
    //更新时只更新修改的内容
    if (StringUtils.hasText(groupRel.getId())) {
      GroupRel tempObj =   groupRelDao.findById(groupRel.getId()).orElse(null);
      Assert.notNull(tempObj, "GroupRel ID：" + groupRel.getId() + " 数据库中没有找到.");
      BeanUtils.copyProperties(groupRel, tempObj, JavaFreeBeanUtils.getNullPropertyNames(groupRel));
      return groupRelDao.save(tempObj);
    }else {
      //新增
      return groupRelDao.save(groupRel);
    }
  }

  @Override
  public PageResult<GroupRel> findGroupRelsByGroupRel(GroupRel groupRel, PageParam pageParam) {
    //通过pageparam 返回Pageable
    Pageable pageable = PageParamUtils.packagePageable(pageParam);
    //条件间的关系是and
    ExampleMatcher matcher = ExampleMatcher.matching()
            //全部模糊查询，即%{username}%
            .withMatcher("groupRelName", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("objName", ExampleMatcher.GenericPropertyMatchers.contains())
            //忽略为空值字段
            .withIgnoreNullValues();
    Example<GroupRel> example = Example.of(groupRel, matcher);
    return PageResult.of(groupRelDao.findAll(example, pageable));
  }

  @Override
  public PageResult<GroupRel> findGroupRelsByGroupRelAny(GroupRel groupRel, PageParam pageParam) {
    //通过pageparam 返回Pageable
    Pageable pageable = PageParamUtils.packagePageable(pageParam);
    //条件间的关系是and
    ExampleMatcher matcher = ExampleMatcher.matchingAny()
            //全部模糊查询，即%{username}%
            .withMatcher("groupRelName", ExampleMatcher.GenericPropertyMatchers.contains())
            .withMatcher("objName", ExampleMatcher.GenericPropertyMatchers.contains())
            //忽略为空值字段
            .withIgnoreNullValues();
    Example<GroupRel> example = Example.of(groupRel, matcher);
    return PageResult.of(groupRelDao.findAll(example, pageable));
  }
}
