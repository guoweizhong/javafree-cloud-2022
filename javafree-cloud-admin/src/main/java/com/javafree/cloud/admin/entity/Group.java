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
 * @Description:    群组表
 * @Database:   表名为 sys_org_group
 */

@Entity
@Table(name ="sys_org_group")
@ApiModel(value = " Group对象 ", description = "群组表")
public class Group  implements Serializable{

	private static final Long serialVersionUID = 3432333289613368220L;

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
	 * 群组名称
	 */
	@ApiModelProperty("群组名称")
  	@Column(name = "group_name")
	private String group_name;

	/**
	 * 群组编码
	 */
	@ApiModelProperty("群组编码")
  	@Column(name = "group_code")
	private String group_code;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
  	@Column(name = "group_order")
	private Integer group_order;

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

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getGroup_code() {
		return group_code;
	}

	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}

	public Integer getGroup_order() {
		return group_order;
	}

	public void setGroup_order(Integer group_order) {
		this.group_order = group_order;
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
