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
 * @Description:    租户与机构的关联表
 * @Database:   table name is sys_org_tenant_dept
 */

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name ="sys_org_tenant_dept")
@Schema(name = " TenantDept POJO ", description = "租户与机构的关联表")
public class TenantDept  implements Serializable{

	private static final Long serialVersionUID = -8644036473145655354L;

	/**
	 * 主键id
	 */
	@Schema(name="id", description = "主键id")
  	@Id
	@GenericGenerator(name = "javafree_uuid", strategy = "com.javafree.cloud.common.id.JavaFreeUUIDGenerator")
	@GeneratedValue(generator = "javafree_uuid")
	@Column(name = "id",length = 22)
	private String id;

	/**
	 * 租户ID
	 */
	@Schema(name="tenantId", description = "租户ID")
  	@Column(name = "tenant_id")
	private String tenantId;

	/**
	 * 机构ID
	 */
	@Schema(name="deptId", description = "机构ID")
  	@Column(name = "dept_id")
	private String deptId;

	/**
	 * 关联名称，由(租户名+关联对象名,用||分隔)名组成
	 */
	@Schema(name="name", description = "关联名称，由(租户名+关联对象名,用||分隔)名组成")
  	@Column(name = "name")
	private String name;

	/**
	 * 排序
	 */
	@Schema(name="relOrder", description = "排序")
  	@Column(name = "rel_order")
	private Integer relOrder;

	/**
	 * 描述
	 */
	@Schema(name="description", description = "描述")
  	@Column(name = "description")
	private String description;

	/**
	 * 创建人
	 */
	@Schema(name="createBy", description = "创建人")
  	@Column(name = "create_by")
	private String createBy;

	/**
	 * 创建时间
	 */
	@Schema(name="createTime", description = "创建时间")
  	@Column(name = "create_time")
  	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新人
	 */
	@Schema(name="updateBy", description = "更新人")
  	@Column(name = "update_by")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@Schema(name="updateTime", description = "更新时间")
  	@Column(name = "update_time")
  	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
