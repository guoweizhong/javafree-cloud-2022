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
 * @Description:    群组表
 * @Database:   table name is sys_org_group
 */

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name ="sys_org_group")
@Schema(name = " Group POJO ", description = "群组表")
public class Group  implements Serializable{

	private static final Long serialVersionUID = 8680431031428398002L;

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
	 * 群组名称
	 */
	@Schema(name = "groupName", description = "群组名称")
	@Column(name = "group_name")
	private String groupName;

	/**
	 * 群组编码
	 */
	@Schema(name = "groupCode", description = "群组编码")
	@Column(name = "group_code")
	private String groupCode;

	/**
	 * 排序
	 */
	@Schema(name = "groupOrder", description = "排序")
	@Column(name = "group_order")
	private Integer groupOrder;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Integer getGroupOrder() {
		return groupOrder;
	}

	public void setGroupOrder(Integer groupOrder) {
		this.groupOrder = groupOrder;
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
