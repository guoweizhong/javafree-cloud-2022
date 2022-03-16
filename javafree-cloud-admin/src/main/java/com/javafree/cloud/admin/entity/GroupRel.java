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
 * @Description:    群组与成员关联表
 * @Database:   表名为 sys_org_group_rel
 */

@Entity
@Table(name ="sys_org_group_rel")
@ApiModel(value = " GroupRel对象 ", description = "群组与成员关联表")
public class GroupRel  implements Serializable{

	private static final Long serialVersionUID = 5458695099846283929L;

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
	 * 群组ID
	 */
	@ApiModelProperty("群组ID")
  	@Column(name = "group_id")
	private String group_id;

	/**
	 * 群组关联对象ID
	 */
	@ApiModelProperty("群组关联对象ID")
  	@Column(name = "obj_id")
	private String obj_id;

	/**
	 * 群组关联对象类型，1.机构部门(DEPT),2.用户(Dept),3.角色(ROLE)
	 */
	@ApiModelProperty("群组关联对象类型，1.机构部门(DEPT),2.用户(Dept),3.角色(ROLE)")
  	@Column(name = "obj_type")
	private String obj_type;

	/**
	 * 关联名称，由(组名+关联对象名,用||分隔)名组成
	 */
	@ApiModelProperty("关联名称，由(组名+关联对象名,用||分隔)名组成")
  	@Column(name = "name")
	private String name;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
  	@Column(name = "rel_order")
	private Integer rel_order;

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

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	public String getObj_id() {
		return obj_id;
	}

	public void setObj_id(String obj_id) {
		this.obj_id = obj_id;
	}

	public String getObj_type() {
		return obj_type;
	}

	public void setObj_type(String obj_type) {
		this.obj_type = obj_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRel_order() {
		return rel_order;
	}

	public void setRel_order(Integer rel_order) {
		this.rel_order = rel_order;
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
