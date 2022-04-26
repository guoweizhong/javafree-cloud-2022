package com.javafree.cloud.admin.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * @Description:    租户信息表
 * @Database:   表名为 sys_org_tenant
 */

@Entity
@Table(name ="sys_org_tenant")
@ApiModel(value = " Tenant对象 ", description = "租户信息表")
public class Tenant  implements Serializable{

	private static final Long serialVersionUID = -440377361092385371L;

	/**
	 * 主键id
	 */
	@ApiModelProperty("主键id")
  	@Id
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator" )
	@GeneratedValue(generator = "uuid2")
	@Column(name = "id",length = 32)
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
	private String tenant_code;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
  	@Column(name = "tenant_order")
	private Integer tenant_order;

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
	private String create_by;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
  	@Column(name = "create_time")
	private Date create_time;

	/**
	 * 更新人
	 */
	@ApiModelProperty("更新人")
  	@Column(name = "update_by")
	private String update_by;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
  	@Column(name = "update_time")
	private Date update_time;

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
	private String ext_data;

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

	public String getTenant_code() {
		return tenant_code;
	}

	public void setTenant_code(String tenant_code) {
		this.tenant_code = tenant_code;
	}

	public Integer getTenant_order() {
		return tenant_order;
	}

	public void setTenant_order(Integer tenant_order) {
		this.tenant_order = tenant_order;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExt_data() {
		return ext_data;
	}

	public void setExt_data(String ext_data) {
		this.ext_data = ext_data;
	}

}
