package com.javafree.cloud.admin.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Description:    机构用户角色关联表
 * @Database:   table name is sys_org_identities
 */

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name ="sys_org_identities")
@Schema(name = " Identities POJO ", description = "机构用户角色关联表")
public class Identities  implements Serializable{

	private static final Long serialVersionUID = 4250354294409075533L;

	/**
	 * 主键id
	 */
	@ApiModelProperty("主键id")
  	@Id
	@GenericGenerator(name = "javafree_uuid", strategy = "com.javafree.cloud.common.id.JavaFreeUUIDGenerator")
	@GeneratedValue(generator = "javafree_uuid")
	@Column(name = "id",length = 22)
	private String id;

	/**
	 * 机构部门ID
	 */
	@ApiModelProperty("机构部门ID")
  	@Column(name = "dept_id")
	private String deptId;

	/**
	 * 用户ID
	 */
	@ApiModelProperty("用户ID")
  	@Column(name = "user_id")
	private String userId;

	/**
	 * 角色ID
	 */
	@ApiModelProperty("角色ID")
  	@Column(name = "role_id")
	private String roleId;

	/**
	 * 角色名称
	 */
	@ApiModelProperty("角色名称")
  	@Column(name = "role_name")
	private String roleName;

	/**
	 * 用户名称
	 */
	@ApiModelProperty("用户名称")
  	@Column(name = "user_name")
	private String userName;

	/**
	 * 机构名称
	 */
	@ApiModelProperty("机构名称")
  	@Column(name = "dept_name")
	private String deptName;

	/**
	 * 排序,决定人在机构下的显示次序
	 */
	@ApiModelProperty("排序,决定人在机构下的显示次序")
  	@Column(name = "rel_order")
	private Integer relOrder;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
  	@Column(name = "description")
	private String description;

	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
  	@Column(name = "create_by")
	private String createBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
  	@Column(name = "create_time")
  	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新人
	 */
	@ApiModelProperty("更新人")
  	@Column(name = "update_by")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
  	@Column(name = "update_time")
  	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 是否为用户首要身份 1是，0否(默认)
	 */
	@ApiModelProperty("是否为用户首要身份 1是，0否(默认)")
  	@Column(name = "primary")
	private String primary;

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

	public void setRelOrder(Integer relOrder) {
		this.relOrder = relOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

}
