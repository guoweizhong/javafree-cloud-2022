package com.javafree.cloud.admin.restapi;

import com.javafree.cloud.admin.entity.Dept;
import com.javafree.cloud.admin.model.TreeNode;
import com.javafree.cloud.admin.service.DeptService;
import com.javafree.cloud.common.api.PageResult;
import com.javafree.cloud.common.api.RestApiParamBody;
import com.javafree.cloud.common.api.RestApiResponse;
import com.javafree.cloud.common.utils.JsonUtils;
import com.javafree.cloud.common.utils.TreeBuiltUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/5/13 11:38
 */
@Slf4j
@RestController
@Api(value = "机构/部门管理相关接口", tags = {"机构/部门管理相关接口"})
@RequestMapping("/dept")
public class DeptRestApi {
    @Autowired
    DeptService deptService;

    @ApiOperation(value = "删除机构/部门", notes = "删除机构/部门")
    @DeleteMapping("/deleteDept")
    public RestApiResponse deleteDept(@RequestParam(name = "id", required = true) String deptid) {
        deptService.deleteDept(deptid);
        log.info("成功删除机构/部门信息，机构/部门ID={}", deptid);
        return RestApiResponse.OK("成功删除机构/部门信息!");
    }

    @ApiOperation(value = "批量删除机构/部门信息", notes = "机构/部门ids用逗号(,)分隔")
    @DeleteMapping("/deleteDeptByIds")
    public RestApiResponse deleteDeptByIds(@RequestParam(name = "ids", required = true) String ids) {
        deptService.deleteDeptByIds(Arrays.asList(ids.split(",")));
        log.info("成功批量删除机构/部门信息，机构/部门IDS={}", ids);
        return RestApiResponse.OK("成功批量删除机构/部门信息!");
    }

    @ApiOperation(value = "获得机构/部门信息")
    @GetMapping("/getDeptById")
    public RestApiResponse<Dept> getDeptById(@RequestParam("id") String id) {
        Dept orgDept = deptService.getDeptById(id);
        if (null == orgDept) {
            log.info("未找到机构/部门信息，机构/部门ID={}", id);
            return RestApiResponse.WARNING("未找到机构/部门信息！", HttpStatus.NO_CONTENT);
        }
        log.info("找到机构/部门信息，机构/部门对象为:{}", JsonUtils.getJsonStringFromObject(orgDept));
        return RestApiResponse.OK(orgDept);
    }


    @ApiOperation(value = "获取所有部门（树形）")
    @GetMapping({"/getAllTree"})
    public RestApiResponse<List<TreeNode>> getAllDeptTree() {
        List<Dept> deptList=deptService.getAllDepts();
        if (null==deptList || deptList.size()<1) {
            log.info("未找到任何机构/部门信息");
            return  RestApiResponse.WARNING("未找到机构/部门信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到机构/部门信息列表");
        List<Dept> deptListTmp= TreeBuiltUtils.builtTree(deptList,Dept.class);
        return  RestApiResponse.OK(toTree(deptListTmp));
    }

    /**
     * 将机构列表转换为前端组件需要的数据格式
     * @param deptList
     * @return
     */
    private List<TreeNode> toTree(List<Dept> deptList){
        Assert.notNull(deptList, "列表数据为空，不能转换");
        List<TreeNode> listTreeNode= new ArrayList<>();
        for (Dept dept:
        deptList) {
            TreeNode treeNode=new TreeNode();
            treeNode.setKey(dept.getId());
            treeNode.setTitle(dept.getDeptName());
            treeNode.setIsLeaf(CollectionUtils.isEmpty(dept.getChildren()));
            treeNode.setOrder(dept.getDeptOrder());
            //如果有子，则处理子列表
            if(!treeNode.getIsLeaf()){
                //递归处理子列表
                treeNode.setChildren(toTree(dept.getChildren()));
            }
            listTreeNode.add(treeNode);
        }
        //按序号升序排,order为空，则放后面
        List<TreeNode> list=listTreeNode.stream().sorted(Comparator.comparing(l -> l.getOrder(), Comparator.nullsLast(Integer::compareTo))).collect(Collectors.toList());
      return  list;
    }
    @ApiOperation(value = "查询机构信息列表,多条件关系为and", notes = "查询机构信息列表,多条件关系为and")
    @PostMapping("/findDeptsByDept")
    public RestApiResponse<PageResult<Dept>> findDeptsByDept(@RequestBody RestApiParamBody<Dept> apiParam) {
        PageResult<Dept>  deptPage=deptService.findDeptsByDeptBySpecification(apiParam.getDataParam(),apiParam.getPageParam());
        if (null==deptPage || deptPage.getTotalElements()<1) {
            log.info("未找到机构信息,传入参数为:{}",JsonUtils.getJsonStringFromObject(apiParam.getDataParam()));
            return  RestApiResponse.WARNING("未找到机构信息!", HttpStatus.NO_CONTENT);
        }
        log.info("找到机构信息列表，传入参数为:{}", JsonUtils.getJsonStringFromObject(apiParam));
        return  RestApiResponse.OK(deptPage);
    }

    @ApiOperation(value = "新增或更新机构/部门信息",notes = "新增或更新机构/部门信息")
    @PostMapping("/saveDept")    //通过@Valid 开启属性值格式校验
    public RestApiResponse<Dept> saveDept(@Valid @RequestBody Dept dept){
        Dept depttemp=deptService.saveDept(dept);
        log.info("机构/部门信息保存成功，机构/部门对象为:{}",JsonUtils.getJsonStringFromObject(dept));
        return  RestApiResponse.OK(depttemp);
    }
}
