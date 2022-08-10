package com.javafree.cloud.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:    机构用户角色关联表
 * @Database:   table name is sys_org_identities
 */

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name ="sys_org_identity")
@Schema(name = " Identity POJO ", description = "机构-用户-角色关联表")
public class Identity implements Serializable{

	private static final Long serialVersionUID = 4250354294409075533L;

	/**
	 * 主键id
	 */
	@Schema(name = "id", description = "主键id")
	@Id
	@GenericGenerator(name = "javafree_uuid", strategy = "com.javafree.cloud.common.id.JavaFreeUUIDGenerator")
	@GeneratedValue(generator = "javafree_uuid")
	@Column(name = "id",length = 22)
	private String id;

	/**
	 * 机构部门ID
	 */
	@Schema(name = "deptId", description = "机构部门ID")
	@Column(name = "dept_id")
	private String deptId;

	/**
	 * 用户ID
	 */
	@Schema(name = "userId", description = "用户ID")
	@Column(name = "user_id")
	private String userId;

	/**
	 * 角色ID
	 */
	@Schema(name = "roleId", description = "角色ID")
	@Column(name = "role_id")
	private String roleId;

	/**
	 * 角色名称
	 */
	@Schema(name = "roleName", description = "角色名称")
	@Column(name = "role_name")
	private String roleName;

	/**
	 * 用户名称
	 */
	@Schema(name = "userName", description = "用户名称")
	@Column(name = "user_name")
	private String userName;

	/**
	 * 机构名称
	 */
	@Schema(name = "deptName", description = "机构名称")
	@Column(name = "dept_name")
	private String deptName;

	/**
	 * 排序,决定人在机构下的显示次序
	 */
	@Schema(name = "relOrder", description = "排序,决定人在机构下的显示次序")
	@Column(name = "rel_order")
	private Integer relOrder;

	/**
	 * 用户姓名
	 */
	@Schema(name = "userRealname", description = "用户姓名")
	@Column(name = "user_realname")
	private String userRealname;

	/**
	 * 创建人
	 */
	@Schema(name = "createBy", description = "创建人")
	@Column(name = "create_by")
	private String createBy;

	/**
	 * 创建时间
	 */
	@Schema(name = "createTime", description = "创建时间")
	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新人
	 */
	@Schema(name = "updateBy", description = "更新人")
	@Column(name = "update_by")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@Schema(name = "updateTime", description = "更新时间")
	@Column(name = "update_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 是否为用户首要身份 1是，0否(默认)
	 */
	@Schema(name = "primaryMark", description = "是否为用户首要身份 1是，0否(默认)")
	@Column(name = "primary_mark")
	private String primaryMark;

	public String getPrimaryMark() {
		return primaryMark;
	}

	public void setPrimaryMark(String primaryMark) {
		this.primaryMark = primaryMark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getRelOrder() {
		return relOrder;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public void setRelOrder(Integer relOrder) {
		this.relOrder = relOrder;
	}


	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


}
