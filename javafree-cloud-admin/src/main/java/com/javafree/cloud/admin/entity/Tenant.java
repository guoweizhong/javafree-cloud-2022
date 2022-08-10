package com.javafree.cloud.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:    租户信息表
 * @Database:   表名为 sys_org_tenant
 */

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name ="sys_org_tenant")
@Schema(name = " Tenant对象 ", description = "租户信息表")
public class Tenant  implements Serializable{

	private static final Long serialVersionUID = 5000011386803060891L;

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
	 * 租户名称
	 */
	@Schema(name = "name", description = "租户名称")
	@Column(name = "name")
	private String name;

	/**
	 * 租户编码，值唯一
	 */
	@Schema(name = "tenantCode", description = "租户编码，值唯一")
	@Column(name = "tenant_code")
	private String tenantCode;

	/**
	 * 排序
	 */
	@Schema(name = "tenantOrder", description = "排序")
	@Column(name = "tenant_order")
	private Integer tenantOrder;

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
	@CreatedDate
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
	@CreatedDate
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

	/**
	 * 状态 1正常 0冻结
	 */
	@Schema(name = "id", description = "状态 1正常 0冻结")
	@Column(name = "status")
	private Integer status;

	/**
	 * 用于字段扩展，可用json格式
	 */
	@Schema(name = "extData", description = "用于字段扩展，可用json格式")
	@Column(name = "ext_data")
	private String extData;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	public Integer getTenantOrder() {
		return tenantOrder;
	}

	public void setTenantOrder(Integer tenantOrder) {
		this.tenantOrder = tenantOrder;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExtData() {
		return extData;
	}

	public void setExtData(String extData) {
		this.extData = extData;
	}

}
