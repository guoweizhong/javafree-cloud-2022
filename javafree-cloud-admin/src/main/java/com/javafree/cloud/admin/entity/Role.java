package com.javafree.cloud.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * @Description:    角色表
 * @Database:   表名为 sys_org_role
 */

@Entity
@Table(name ="sys_org_role")
@Schema(name = " Role对象 ", description = "角色表")
public class Role  implements Serializable{
	private static final Long serialVersionUID = -580842572025346573L;

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
	 * 角色名称
	 */
	@Schema(name = "roleName", description = "角色名称")
	@Column(name = "role_name")
	private String roleName;

	/**
	 * 角色编码
	 */
	@Schema(name = "roleCode", description = "角色编码")
	@Column(name = "role_code")
	private String roleCode;

	/**
	 * 角色类型，1默认，2职称、3岗位
	 */
	@Schema(name = "roleType", description = "角色类型，1默认，2职称、3岗位")
	@Column(name = "role_type")
	private String roleType;

	/**
	 * 排序
	 */
	@Schema(name = "roleOrder", description = "排序")
	@Column(name = "role_order")
	private Integer roleOrder;

	/**
	 * 描述
	 */
	@Schema(name = "description", description = "描述")
	@Column(name = "description")
	private String description;

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

	@Schema(name = "createTimeEnd", description = "创建时间查询，只用于查询，不持久")
	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTimeEnd;

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

	@Schema(name = "updateTimeEnd", description = "更新时间查询，只用于查询，不持久")
	@Transient
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTimeEnd;

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Integer getRoleOrder() {
		return roleOrder;
	}

	public void setRoleOrder(Integer roleOrder) {
		this.roleOrder = roleOrder;
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

}
