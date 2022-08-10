package com.javafree.cloud.admin.service.impl;

import com.javafree.cloud.admin.dao.DeptDao;
import com.javafree.cloud.admin.entity.Dept;
import com.javafree.cloud.admin.service.DeptService;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
 * @Date 2021/12/15 11:21
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptDao deptDao;

    /**
     * 注意更新和删除是需要加事务的， 并且要加上 @Modifying的注解
     * clearAutomatically清除底层持久化上下文
     *
     * @param dept
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class)
    @Modifying(clearAutomatically = true)
    @Override
    public Dept saveDept(Dept dept) {
        Assert.notNull(dept, "Dept 对象不能为空.");
        //更新时只更新修改的内容
        if (StringUtils.hasText(dept.getId())) {
            Dept tempObj = new Dept();
            tempObj = deptDao.findById(dept.getId()).orElse(null);
            Assert.notNull(tempObj, "Dept ID：" + dept.getId() + " 数据库中没有找到.");
            //将传入的Dept对象值copy到tempObj对象中，并忽略Dept对象为空的属性
            BeanUtils.copyProperties(dept, tempObj, JavaFreeBeanUtils.getNullPropertyNames(dept));
            return deptDao.save(tempObj);
        }
        //新增
        return deptDao.save(dept);
    }


    @Override
    public void deleteDeptByIds(List<String> ids) {
        deptDao.deleteDeptByIds(ids);
    }

    @Override
    public void deleteDept(String id) {
        deptDao.deleteDeptById(id);
    }

    @Override
    public Dept getDeptById(String id) {
        return deptDao.findById(id).orElse(null);
    }

    @Override
    public Dept getDeptByName(String deptName) {
        return deptDao.getUserByName(deptName);
    }

    @Override
    public List<Dept> getDeptsByParentId(String parentid) {
        return null;
    }

    @Override
    public long getCountOfDept(String parentId) {
        return deptDao.getCountOfDept(parentId);
    }

    @Override
    public List<Dept> getAllDepts() {
        return deptDao.getAllDepts();
    }
    @Override
    public PageResult<Dept> findDeptsByDeptBySpecification(Dept dept, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        Specification<Dept> specification = (Specification<Dept>) (root, criteriaQuery, criteriaBuilder) -> {
            // 查询条件的集合
            List<Predicate> list = new ArrayList<>();
            //父ID有值，则为等于，否则为is null 取根记录
            if(StringUtils.hasText(dept.getParentId()))
            {
                list.add(criteriaBuilder.equal(root.get("parentId"),dept.getParentId()));
            }else {
                list.add(criteriaBuilder.isNull(root.get("parentId")));
            }
            if(StringUtils.hasText(dept.getDescription()))
            {
                list.add(criteriaBuilder.like(root.get("description"),"%"+dept.getDescription()+"%"));
            }
            if(StringUtils.hasText(dept.getShortname()))
            {
                list.add(criteriaBuilder.like(root.get("shortname"),"%"+dept.getShortname()+"%"));
            }
            if(StringUtils.hasText(dept.getDeptName()))
            {
                list.add(criteriaBuilder.like(root.get("deptName"),"%"+dept.getDeptName()+"%"));
            }
            if(StringUtils.hasText(dept.getFlag()))
            {
                list.add(criteriaBuilder.equal(root.get("flag"),dept.getFlag()));
            }
            if(StringUtils.hasText(dept.getOrgCode()))
            {
                list.add(criteriaBuilder.equal(root.get("orgCode"),dept.getOrgCode()));
            }
            if(StringUtils.hasText(dept.getOrgType()))
            {
                list.add(criteriaBuilder.equal(root.get("orgType"),dept.getOrgType()));
            }
            if(StringUtils.hasText(dept.getOrgCategory()))
            {
                list.add(criteriaBuilder.equal(root.get("orgCategory"),dept.getOrgCategory()));
            }
            if( dept.getStatus()!=null&&1==dept.getStatus()&& 0==dept.getStatus())
            {
                list.add(criteriaBuilder.equal(root.get("status"),dept.getStatus()));
            }
            // 转数组
            Predicate[] predicates = new Predicate[list.size()];
            list.toArray(predicates);
            return criteriaBuilder.and(predicates);
        };
        Page<Dept> deptsPage = deptDao.findAll(specification, pageable);
        return PageResult.of(deptsPage);
    }


    @Override
    public PageResult<Dept> findDeptsByDept(Dept dept, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matching()
                //忽略为空值字段
                .withIgnoreNullValues()
                .withIgnoreCase()
                //全部模糊查询，即%{name}%
                .withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
                .withMatcher("deptName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Dept> example = Example.of(dept, matcher);
        return PageResult.of(deptDao.findAll(example, pageable));
    }

    public Page<Dept> findVenueList(Pageable pageable,String name,String [] hobbies,Integer age, String brirth) {
        Specification<Dept> specification=new Specification<Dept>() {
            @Override
            public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> listAnd=new ArrayList<>(); //组装and语句
                if(StringUtils.hasText(name)) {
                    listAnd.add(criteriaBuilder.like(root.get("name"), "%" + name));  //姓名 模糊查询
                }
                if(age!=null) {
                    listAnd.add(criteriaBuilder.equal(root.get("age"), age)); //年龄
                }
                if(StringUtils.hasText(brirth)) {
                    listAnd.add(criteriaBuilder.like(root.get("brirth"), "%" + brirth + "%")); //出生地 模糊查询
                }
                Predicate predicateAnd = criteriaBuilder.and(listAnd.toArray(new Predicate[listAnd.size()])); //AND查询加入查询条件
                List<Predicate> listOr = new ArrayList<>();///组装or语句
                if(hobbies!=null && hobbies.length>0) {
                    for (String hoobbie : hobbies) {
                        //爱好多选 用OR链接
                        listOr.add(criteriaBuilder.equal(root.get("hobbie"), hoobbie));
                    }
                }
                Predicate predicateOR = criteriaBuilder.or(listOr.toArray(new Predicate[listOr.size()])); //OR查询加入查询条件
                return criteriaQuery.where(predicateAnd,predicateOR).getRestriction();
            }
        };
        Page<Dept> page=deptDao.findAll(specification,pageable);
        return page;
    }

    @Override
    public PageResult<Dept> findDeptsByDeptAny(Dept dept, PageParam pageParam) {
        //通过pageparam 返回Pageable
        Pageable pageable = PageParamUtils.packagePageable(pageParam);
        //条件间的关系是and
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                //忽略为空值字段
                .withIgnoreNullValues()
                .withIgnoreCase()
                //全部模糊查询，即%{name}%
                .withMatcher("deptName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Dept> example = Example.of(dept, matcher);
        return PageResult.of(deptDao.findAll(example, pageable));
    }
}
