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
 * @Description:    组织机构表
 * @Database:   表名为 sys_org_dept
 */

@Entity
@Table(name ="sys_org_dept")
@ApiModel(value = " Dept对象 ", description = "组织机构表")
public class Dept  implements Serializable{

	private static final Long serialVersionUID = -2419529423447976643L;

	/**
	 * ID
	 */
	@ApiModelProperty("ID")
  	@Id
	// 因出现 WARN  org.hibernate.id.UUIDHexGenerator:42 - HHH000409: Using org.hibernate.id.UUIDHexGenerator which does not generate IETF RFC 4122 compliant
	//@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	//@GeneratedValue(generator = "jpa-uuid")
	//所以换成下面两行
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator" )
	@GeneratedValue(generator = "uuid2")
	@Column(name = "id",length = 32)
	private String id;

	/**
	 * 父机构ID
	 */
	@ApiModelProperty("父机构ID")
  	@Column(name = "parent_id")
	private String parent_id;

	/**
	 * 机构/部门名称
	 */
	@ApiModelProperty("机构/部门名称")
  	@Column(name = "dept_name")
	private String dept_name;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
  	@Column(name = "dept_order")
	private Integer dept_order;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
  	@Column(name = "description")
	private String description;

	/**
	 * 机构类别 1行政机构(默认)，2党组机构，3其他可扩展
	 */
	@ApiModelProperty("机构类别 1行政机构(默认)，2党组机构，3其他可扩展")
  	@Column(name = "org_category")
	private String org_category;

	/**
	 * 机构类型 1一级部门 2子部门
	 */
	@ApiModelProperty("机构类型 1一级部门 2子部门")
  	@Column(name = "org_type")
	private String org_type;

	/**
	 * 机构编码
	 */
	@ApiModelProperty("机构编码")
  	@Column(name = "org_code")
	private String org_code;

	/**
	 * 备注
	 */
	@ApiModelProperty("备注")
  	@Column(name = "memo")
	private String memo;

	/**
	 * 状态（1启用，0不启用）
	 */
	@ApiModelProperty("状态（1启用，0不启用）")
  	@Column(name = "status")
	private String status;

	/**
	 * 删除状态（0，正常，1已删除）
	 */
	@ApiModelProperty("删除状态（0，正常，1已删除）")
  	@Column(name = "del_flag")
	private String del_flag;

	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
  	@Column(name = "create_by")
	private String create_by;

	/**
	 * 创建日期
	 */
	@ApiModelProperty("创建日期")
  	@Column(name = "create_time")
	private Date create_time;

	/**
	 * 更新人
	 */
	@ApiModelProperty("更新人")
  	@Column(name = "update_by")
	private String update_by;

	/**
	 * 更新日期
	 */
	@ApiModelProperty("更新日期")
  	@Column(name = "update_time")
	private Date update_time;

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

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public Integer getDept_order() {
		return dept_order;
	}

	public void setDept_order(Integer dept_order) {
		this.dept_order = dept_order;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrg_category() {
		return org_category;
	}

	public void setOrg_category(String org_category) {
		this.org_category = org_category;
	}

	public String getOrg_type() {
		return org_type;
	}

	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}

	public String getOrg_code() {
		return org_code;
	}

	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
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

	public String getExt_data() {
		return ext_data;
	}

	public void setExt_data(String ext_data) {
		this.ext_data = ext_data;
	}

}
