package com.javafree.cloud.admin.service.impl;

import com.javafree.cloud.admin.dao.IdentityDao;
import com.javafree.cloud.admin.entity.Identity;
import com.javafree.cloud.admin.service.IdentityService;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageParamUtils;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.utils.JavaFreeBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/11 14:22
 */
@Service
public class IdentityServiceImpl implements IdentityService {
    @Autowired
    IdentityDao identityDao;

    @Override
    public Identity getIdentityById(String id) {
        return identityDao.findById(id).orElse(null);
    }

    @Override
    public PageResult<Identity> findIdentitysByIdentity(Identity identity, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matching()
                //全部模糊查询
                .withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("deptName", ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略为空值字段
                .withIgnoreNullValues();
        Example<Identity> example = Example.of(identity, matcher);

        return PageResult.of(identityDao.findAll(example, pageable));
    }

    @Override
    public PageResult<Identity> findIdentitysByIdentityAny(Identity identity, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                //全部模糊查询
                .withMatcher("userName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("roleName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("deptName", ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略为空值字段
                .withIgnoreNullValues();
        Example<Identity> example = Example.of(identity, matcher);
        return PageResult.of(identityDao.findAll(example, pageable));
    }

    /**
     * 根据机构ID查询，查询机构下的成员列表，机构ID与其他条件之间是and关系，其他属性条件之间是or关系
     * @param identity
     * @param pageParam
     * @return
     */
    public PageResult<Identity> findIdentitysByDeptID(Identity identity, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        Specification<Identity> specification=new Specification<Identity>() {
            @Override
            public Predicate toPredicate(Root<Identity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> listAnd=new ArrayList<>(); //组装and语句
                if(StringUtils.hasText(identity.getDeptId())) {
                    listAnd.add(criteriaBuilder.equal(root.get("deptId"),  identity.getDeptId()));  //姓名 模糊查询
                }
                Predicate predicateAnd = criteriaBuilder.and(listAnd.toArray(new Predicate[listAnd.size()])); //AND查询加入查询条件
                List<Predicate> listOr = new ArrayList<>();///组装or语句
                if(StringUtils.hasText(identity.getUserName())) {
                    listOr.add(criteriaBuilder.like(root.get("userName"), "%" + identity.getUserName() + "%")); ///用户登录名 模糊查询
                }
                if(StringUtils.hasText(identity.getRoleName())) {
                    listOr.add(criteriaBuilder.like(root.get("roleName"), "%" + identity.getRoleName() + "%")); ///角色名 模糊查询
                }
                if(StringUtils.hasText(identity.getDeptName())) {
                    listOr.add(criteriaBuilder.like(root.get("deptName"), "%" + identity.getDeptName() + "%")); ///机构名 模糊查询
                }
                if(StringUtils.hasText(identity.getUserRealname())) {
                    listOr.add(criteriaBuilder.like(root.get("userRealname"), "%" + identity.getUserRealname() + "%")); ///用户姓名 模糊查询
                }
                Predicate predicateOR = criteriaBuilder.or(listOr.toArray(new Predicate[listOr.size()])); //OR查询加入查询条件

                Predicate predicateAll=null;
                //防止生成1=1 and 0=1  条件，会报sql injection violation, dbType mysql, druid-version 1.2.8, double const condition 异常
                if(listAnd.size()>0&&listOr.size()>0){
                    predicateAll=  criteriaQuery.where(predicateAnd,predicateOR).getRestriction();
                }else if(listAnd.size()>0){
                    predicateAll=  criteriaQuery.where(predicateAnd).getRestriction();
                }else if(listOr.size()>0){
                    predicateAll=  criteriaQuery.where(predicateOR).getRestriction();
                }
                return predicateAll;
            }
        };
        return PageResult.of(identityDao.findAll(specification,pageable));
    }

    @Override
    public Identity saveIdentity(Identity identity) {
        Assert.notNull(identity, "Identity 对象不能为空.");
        //更新时只更新修改的内容
        if (StringUtils.hasText(identity.getId())) {
            Identity tempObj = identityDao.findById(identity.getId()).orElse(null);
            Assert.notNull(tempObj, "Identity ID：" + identity.getId() + " 数据库中没有找到.");
            //将传入的Identity对象值copy到tempObj对象中，并忽略Identity对象为空的属性
            BeanUtils.copyProperties(identity, tempObj, JavaFreeBeanUtils.getNullPropertyNames(identity));
            return identityDao.save(tempObj);
        }else {
            //如果userid,roleid,deptid为条件，已经有内容，则返回，避免报违反唯一值异常
            if(identityDao.findIdentityCountByRelID(identity)>0){
                Assert.isTrue(identityDao.findIdentityCountByRelID(identity)>0,"违反数据库唯一键，数据库中已经有此记录。");
                return null;
            }
            //新增
            return identityDao.save(identity);
        }
    }

    @Override
    public void deleteIdentityById(String id) {
        identityDao.deleteIdentityById(id);
    }

    @Override
    public void deleteIdentityByIds(List<String> ids) {
        identityDao.deleteIdentityByIds(ids);
    }

    @Override
    public void deleteIdentityByRoleId(String id) {
        identityDao.deleteIdentityByRoleId(id);
    }

    @Override
    public void deleteIdentityByRoleIds(List<String> ids) {
        identityDao.deleteIdentityByRoleIds(ids);
    }

    @Override
    public void deleteIdentityByUserId(String id) {
        identityDao.deleteIdentityByUserId(id);
    }

    @Override
    public void deleteIdentityByUserIds(List<String> ids) {
        identityDao.deleteIdentityByUserIds(ids);
    }

    @Override
    public void deleteIdentityByDeptId(String id) {
        identityDao.deleteIdentityByDeptId(id);
    }

    @Override
    public void deleteIdentityByDeptIds(List<String> ids) {
        identityDao.deleteIdentityByDeptIds(ids);
    }

    @Override
    public List<Object[]> countDeptsByDeptId() {
        return identityDao.countDeptsByDeptId();
    }

    @Override
    public List<Object[]> countRolesByRoleId() {
        return identityDao.countRolesByRoleId();
    }
}
