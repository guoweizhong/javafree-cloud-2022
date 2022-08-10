package com.javafree.cloud.admin.restapi;

import com.javafree.cloud.admin.entity.Dept;
import com.javafree.cloud.admin.entity.Identity;
import com.javafree.cloud.admin.entity.Role;
import com.javafree.cloud.admin.entity.User;
import com.javafree.cloud.admin.model.TreeNode;
import com.javafree.cloud.admin.service.DeptService;
import com.javafree.cloud.admin.service.IdentityService;
import com.javafree.cloud.admin.service.RoleService;
import com.javafree.cloud.admin.service.UserService;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.api.RestApiParamBody;
import com.javafree.cloud.common.api.RestApiResponse;
import com.javafree.cloud.common.utils.JsonUtils;
import com.javafree.cloud.common.utils.TreeBuiltUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/7/11 15:33
 */
@Slf4j
@RestController
@Tag(name = "IdentityRestApi", description ="机构成员关系管理接口")
@RequestMapping("/identity")
public class IdentityRestApi {
    @Autowired
    IdentityService identityService;

    @Autowired
    DeptService deptService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Operation(summary = "删除机构成员关系", description = "删除机构成员关系")
    @DeleteMapping("/deleteIdentity")
    public RestApiResponse deleteIdentity(@RequestParam(name = "id", required = true) String identityid) {
        Assert.notNull(identityid, "机构成员关系ID不能为空");
        identityService.deleteIdentityById(identityid);
        log.info("成功删除机构成员关系信息，机构成员关系ID={}", identityid);
        return RestApiResponse.OK("成功删除机构成员关系信息!");
    }

    @Operation(summary = "批量删除机构成员关系信息", description = "机构成员关系ids用逗号(,)分隔")
    @DeleteMapping("/deleteIdentityByIds")
    public RestApiResponse deleteIdentityByIds(@RequestParam(name = "ids", required = true) String ids) {
        Assert.notNull(ids, "机构成员关系ID不能为空");
        identityService.deleteIdentityByIds(Arrays.asList(ids.split(",")));
        log.info("成功批量删除机构成员关系信息，机构成员关系IDS={}", ids);
        return RestApiResponse.OK("成功批量删除机构成员关系信息!");
    }

    @Operation(summary = "获得机构成员关系信息")
    @GetMapping("/getIdentityById")
    public RestApiResponse<Identity> getIdentityById(@RequestParam("id") String id) {
        Assert.notNull(id, "机构成员关系ID不能为空");
        Identity orgIdentity = identityService.getIdentityById(id);
        if (null == orgIdentity) {
            log.info("未找到机构成员关系信息，机构成员关系ID={}", id);
            return RestApiResponse.WARNING("未找到机构成员关系信息！", HttpStatus.NO_CONTENT);
        }
        log.info("找到机构成员关系信息，机构成员关系对象为:{}", JsonUtils.getJsonStringFromObject(orgIdentity));
        return RestApiResponse.OK(orgIdentity);
    }

    @Operation(summary = "查询机构成员关系信息列表,多条件关系为and", description = "查询机构成员关系信息列表,多条件关系为and")
    @PostMapping("/findIdentitysByIdentity")
    public RestApiResponse<PageResult<Identity>> findIdentitysByIdentity(@RequestBody RestApiParamBody<Identity> apiParam) {

        PageResult<Identity> identityPage = identityService.findIdentitysByIdentity(apiParam.getDataParam(), apiParam.getPageParam());
        if (null == identityPage || identityPage.getTotalElements() < 1) {
            log.info("未找到机构成员关系信息,传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
            return RestApiResponse.WARNING("未找到机构成员关系信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到机构成员关系信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
        return RestApiResponse.OK(identityPage);
    }

    @Operation(summary = "查询机构成员关系信息列表,多条件关系为OR",description = "查询机构成员关系信息列表,多条件关系为OR")
    @PostMapping("/findIdentitysByIdentityAny")
    public RestApiResponse<PageResult<Identity>> findIdentitysByIdentityAny(@RequestBody RestApiParamBody<Identity> apiParam) {
        PageResult<Identity>  identityPage=identityService.findIdentitysByIdentityAny(apiParam.getDataParam(),apiParam.getPageParam());
        if (null==identityPage || identityPage.getTotalElements()<1) {
            log.info("未找到机构成员关系信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam));
            return  RestApiResponse.WARNING("未找到机构成员关系信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到机构成员关系信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
        return  RestApiResponse.OK(identityPage);
    }
    @Operation(summary = "查询某机构ID下的员关系信息列表",description = "查询某机构ID下的员关系信息列表,除去机构ID，其他属性条件关系为OR")
    @PostMapping("/findIdentitysByDeptID")
    public RestApiResponse<PageResult<Identity>> findIdentitysByDeptID(@RequestBody RestApiParamBody<Identity> apiParam) {

        //为了前端提供关键字查询

        log.info("找到机构成员关系信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
        PageResult<Identity>  identityPage=identityService.findIdentitysByDeptID(apiParam.getDataParam(),apiParam.getPageParam());
        if (null==identityPage || identityPage.getTotalElements()<1) {
            log.info("未找到机构成员关系信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam));
            return  RestApiResponse.WARNING("未找到机构成员关系信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到机构成员关系信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
        return  RestApiResponse.OK(identityPage);
    }


    @Operation(summary = "新增或更新机构成员关系信息", description = "新增或更新机构成员关系信息")
    @PostMapping("/saveIdentity")
    public RestApiResponse<Identity> saveIdentity(@Valid @RequestBody Identity identity) {
        Assert.notNull(identity, "机构成员关系信息不能为空");
        Assert.notNull(identity.getDeptId(), "机构ID不能为空");
        Assert.notNull(identity.getUserId(), "用户ID不能为空");
        Assert.notNull(identity.getRoleId(), "角色ID不能为空");
        if (!StringUtils.hasText(identity.getDeptName())){
          Dept dept=  deptService.getDeptById(identity.getDeptId());
          Assert.notNull(dept, "没有找到该机构信息，机构ID为："+identity.getDeptId());
            identity.setDeptName(dept.getDeptName());
        }
        if (!StringUtils.hasText(identity.getRoleName())){
            Role role=  roleService.getRoleById(identity.getRoleId());
            Assert.notNull(role, "没有找到该角色信息，角色ID为："+identity.getRoleId());
            identity.setRoleName(role.getRoleName());
        }
        if (!StringUtils.hasText(identity.getUserName())){
            User user=  userService.getUserById(identity.getUserId());
            Assert.notNull(user, "没有找到该用户信息，用户ID为："+identity.getUserId());
            identity.setUserName(user.getUsername());
            identity.setUserRealname(user.getRealname());
        }
        Identity orgIdentity = identityService.saveIdentity(identity);
        log.info("机构成员关系信息保存成功，机构成员关系对象为:{}", JsonUtils.getJsonStringFromObject(identity));
        return RestApiResponse.OK(orgIdentity);
    }

    @Operation(summary = "获取所有部门（树形）及包括的关联成员数量")
    @GetMapping({"/getAllDeptCountTree"})
    public RestApiResponse<List<TreeNode>> getAllDeptCountTree() {
        List<Dept> deptList=deptService.getAllDepts();
        if (null==deptList || deptList.size()<1) {
            log.info("未找到任何机构/部门信息");
            return  RestApiResponse.WARNING("未找到机构/部门信息!", HttpStatus.NO_CONTENT);
        }
        List<Object[]> deptCountList=identityService.countDeptsByDeptId();

        Map<String,Integer> deptCountMap=deptCountList.stream().collect(
                Collectors.toMap(x-> String.valueOf(x[0]),x->Long.valueOf((Long) x[1]).intValue())
        );
        //将部门列表，变为树形列表
        List<Dept> deptListTmp= TreeBuiltUtils.builtTree(deptList,Dept.class);
        return  RestApiResponse.OK(toCombinationTree(deptListTmp,deptCountMap));
    }

    /**
     * 将机构列表转换为前端组件需要的数据格式
     * @param deptList
     * @return
     */
    private List<TreeNode> toCombinationTree(List<Dept> deptList,Map deptCountMap){
        Assert.notNull(deptList, "列表数据为空，不能转换");
        List<TreeNode> listTreeNode= new ArrayList<>();
        if(deptCountMap==null){
            deptCountMap=new HashMap<String,Integer>();
        }
        for (Dept dept:
                deptList) {
            TreeNode treeNode=new TreeNode();
            treeNode.setKey(dept.getId());
            treeNode.setTitle(dept.getDeptName());
            treeNode.setIsLeaf(CollectionUtils.isEmpty(dept.getChildren()));
            treeNode.setOrder(dept.getDeptOrder());
            Integer inttemp=deptCountMap.get(dept.getId())!=null?(Integer)deptCountMap.get(dept.getId()):0;
            treeNode.setCount(inttemp);
            //如果有子，则处理子列表
            if(!treeNode.getIsLeaf()){
                //递归处理子列表
                treeNode.setChildren(toCombinationTree(dept.getChildren(),deptCountMap));
            }
            listTreeNode.add(treeNode);
        }
        //按序号升序排,order为空，则放后面
        List<TreeNode> list=listTreeNode.stream().sorted(Comparator.comparing(l -> l.getOrder(), Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
        return  list;
    }
}
