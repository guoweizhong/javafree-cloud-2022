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
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Description:    组织机构表
 * @Database:   table name is sys_org_dept
 */

@Entity
@Table(name ="sys_org_dept")
@Schema(name = " Dept POJO ", description = "组织机构表")
public class Dept  implements Serializable{

	private static final Long serialVersionUID = 7376922721481619537L;

	/**
	 * ID
	 */
	@ApiModelProperty("ID")
  	@Id
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid2")
	@Column(name = "id",length = 36)
	private String id;

	/**
	 * 父机构ID
	 */
	@ApiModelProperty("父机构ID")
  	@Column(name = "parent_id")
	private String parentId;

	/**
	 * 机构/部门名称
	 */
	@ApiModelProperty("机构/部门名称")
  	@Column(name = "dept_name")
	private String deptName;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
  	@Column(name = "dept_order")
	private Integer deptOrder;

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
	private String orgCategory;

	/**
	 * 机构类型 1机构(相对独立的单位) 2子部门
	 */
	@ApiModelProperty("机构类型 1机构(相对独立的单位) 2子部门")
  	@Column(name = "org_type")
	private String orgType;

	/**
	 * 机构编码,只有机类型是机构时本身编码，当是部门类型时，是所属机构的编码
	 */
	@ApiModelProperty("机构编码,只有机类型是机构时本身编码，当是部门类型时，是所属机构的编码")
  	@Column(name = "org_code")
	private String orgCode;

	/**
	 * 机构简称，类型是机构时显示
	 */
	@ApiModelProperty("机构简称，类型是机构时显示")
  	@Column(name = "shortname")
	private String shortname;

	/**
	 * 状态（1启用，0不启用）
	 */
	@ApiModelProperty("状态（1启用，0不启用）")
  	@Column(name = "status")
	private String status;

	/**
	 * 标识用于业务扩展，如（1列选，0不列选）或其他状态
	 */
	@ApiModelProperty("标识用于业务扩展，如（1列选，0不列选）或其他状态")
  	@Column(name = "flag")
	private String flag;

	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
  	@Column(name = "create_by")
	private String createBy;

	/**
	 * 创建日期
	 */
	@ApiModelProperty("创建日期")
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
	 * 更新日期
	 */
	@ApiModelProperty("更新日期")
  	@Column(name = "update_time")
  	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptOrder() {
		return deptOrder;
	}

	public void setDeptOrder(Integer deptOrder) {
		this.deptOrder = deptOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrgCategory() {
		return orgCategory;
	}

	public void setOrgCategory(String orgCategory) {
		this.orgCategory = orgCategory;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getExtData() {
		return extData;
	}

	public void setExtData(String extData) {
		this.extData = extData;
	}

}
