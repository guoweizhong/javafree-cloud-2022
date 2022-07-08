package com.javafree.cloud.admin.service.impl;

import com.javafree.cloud.admin.dao.TenantDao;
import com.javafree.cloud.admin.entity.Tenant;
import com.javafree.cloud.admin.service.TenantService;
import com.javafree.cloud.common.api.PageParam;
import com.javafree.cloud.common.api.PageParamUtils;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.utils.JavaFreeBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/4/22 16:40
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    TenantDao tenantDao;

    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modifying的注解
     * clearAutomatically清除底层持久化上下文
     *
     * @param tenant
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    @Override
    public Tenant saveTenant(Tenant tenant) {
        Assert.notNull(tenant, "Tenant 对象不能为空.");
        //更新时只更新修改的内容
        if (StringUtils.hasText(tenant.getId())) {
            Tenant tempObj = tenantDao.findById(tenant.getId()).orElse(null);
            Assert.notNull(tempObj, "Tenant ID：" + tenant.getId() + " 数据库中没有找到.");
            //将传入的Tenant对象值copy到tempObj对象中，并忽略Tenant对象为空的属性
            BeanUtils.copyProperties(tenant, tempObj, JavaFreeBeanUtils.getNullPropertyNames(tenant));
            return tenantDao.save(tempObj);
        }
        //新增
        return tenantDao.save(tenant);
    }

    @Override
    public void deleteTenantByIds(List<String> ids) {
        tenantDao.deleteTenantByIds(ids);
    }

    @Override
    public void deleteTenant(String id) {
        tenantDao.deleteTenantById(id);
    }

    @Override
    public Tenant getTenantById(String id) {
        return tenantDao.findById(id).orElse(null);
    }

    @Override
    public Tenant getTenantByCode(String code) {
        return tenantDao.getTenantByCode(code);
    }

    @Override
    public PageResult<Tenant> findTenantsByName(String name, PageParam pageParam) {
        Tenant tenant = new Tenant();
        tenant.setName(name);
        return findTenantsByTenant(tenant, pageParam);
    }

    /**
     * 以租户对象为条件返回租户分页列表数据,条件关系为and
     *
     * @param tenant
     * @param pageParam
     * @return
     */
    @Override
    public PageResult<Tenant> findTenantsByTenant(Tenant tenant, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        return PageResult.of(findAllByConditions(tenant, pageable));
    }


    /**
     * 以租户对象为条件返回租户分页列表数据,条件关系为or
     *
     * @param tenant
     * @param pageParam
     * @return
     */
    @Override
    public PageResult<Tenant> findTenantsByTenantAny(Tenant tenant, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                //忽略为空值字段
                .withIgnoreNullValues()
                //全部模糊查询，即%{name}%
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Tenant> example = Example.of(tenant, matcher);
        return PageResult.of(tenantDao.findAll(example, pageable));
    }

    /**
     * 构造查询条件
     *
     * @param tenant
     * @param page
     * @return
     */
    private Page<Tenant> findAllByConditions(Tenant tenant, Pageable page) {
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matching()
                //忽略为空值字段
                .withIgnoreNullValues()
                //因为createTime用于范围查询，所以将等于的条件排除掉
                .withIgnorePaths("createTime")
                //全部模糊查询，即%{name}%
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());

        Example<Tenant> example = Example.of(tenant, matcher);
        return tenantDao.findAll(getSpecFromDatesAndExample(tenant.getCreateTime(), tenant.getCreateTimeEnd(), example), page);
    }

    /**
     * 构造日期范围查询条件
     *
     * @param from
     * @param to
     * @param example
     * @return
     */
    private Specification<Tenant> getSpecFromDatesAndExample(
            Date from, Date to, Example<Tenant> example) {

        return (Specification<Tenant>) (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (from != null) {
                predicates.add(builder.greaterThan(root.get("createTime"), from));
            }
            if (to != null) {
                predicates.add(builder.lessThan(root.get("createTime"), to));
            }
            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
