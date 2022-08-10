package com.javafree.cloud.admin.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Description:    租户信息表
 * @Database:   table name is sys_org_tenant
 */

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name ="sys_org_tenant")
@Schema(name = " Tenant POJO ", description = "租户信息表")
public class Tenant  implements Serializable{

	private static final Long serialVersionUID = 6659642190339024351L;

	/**
	 * 主键id
	 */
	@ApiModelProperty("主键id")
  	@Id
	@GenericGenerator(name = "javafree_uuid", strategy = "com.javafree.cloud.common.id.JavaFreeUUIDGenerator")
	@Column(name = "id",length = 22)
	private String id;

	/**
	 * 租户名称
	 */
	@ApiModelProperty("租户名称")
  	@Column(name = "name")
	private String name;

	/**
	 * 租户编码，值唯一
	 */
	@ApiModelProperty("租户编码，值唯一")
  	@Column(name = "tenant_code")
	private String tenantCode;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
  	@Column(name = "tenant_order")
	private Integer tenantOrder;

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
	 * 状态 1正常 0冻结
	 */
	@ApiModelProperty("状态 1正常 0冻结")
  	@Column(name = "status")
	private Integer status;

	/**
	 * 用于字段扩展，可用json格式
	 */
	@ApiModelProperty("用于字段扩展，可用json格式")
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
