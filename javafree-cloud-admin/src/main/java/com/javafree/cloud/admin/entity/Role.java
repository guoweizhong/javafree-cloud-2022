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
 * @Description:    角色表
 * @Database:   表名为 sys_org_role
 */

@Entity
@Table(name ="sys_org_role")
@ApiModel(value = " Role对象 ", description = "角色表")
public class Role  implements Serializable{

	private static final Long serialVersionUID = -978713274439333041L;

	/**
	 * 主键id
	 */
	@ApiModelProperty("主键id")
  	@Id
	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	@GeneratedValue(generator = "jpa-uuid")
	@Column(name = "id",length = 32)
	private String id;

	/**
	 * 角色名称
	 */
	@ApiModelProperty("角色名称")
  	@Column(name = "role_name")
	private String role_name;

	/**
	 * 角色编码
	 */
	@ApiModelProperty("角色编码")
  	@Column(name = "role_code")
	private String role_code;

	/**
	 * 角色类型，1默认，2职称、3岗位
	 */
	@ApiModelProperty("角色类型，1默认，2职称、3岗位")
  	@Column(name = "role_type")
	private String role_type;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
  	@Column(name = "role_order")
	private Integer role_order;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getRole_type() {
		return role_type;
	}

	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}

	public Integer getRole_order() {
		return role_order;
	}

	public void setRole_order(Integer role_order) {
		this.role_order = role_order;
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

}
