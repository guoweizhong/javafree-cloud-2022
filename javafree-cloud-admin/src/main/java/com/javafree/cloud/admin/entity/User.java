package com.javafree.cloud.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * @Description:    用户表
 * @Database:   table name is sys_org_user
 */

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name ="sys_org_user")
@ApiModel(value = " User POJO ", description = "用户表")
public class User  implements Serializable{

	private static final Long serialVersionUID = 340634693098971099L;

	/**
	 * 主键id
	 */
	@ApiModelProperty("主键id")
	@Id
	@GenericGenerator(name = "javafree_uuid", strategy = "com.javafree.cloud.common.id.JavaFreeUUIDGenerator")
	@GeneratedValue(generator = "javafree_uuid")
	@Column(name = "id",length = 22)
	private String id;

	/**
	 * 登录账号，唯一
	 */
	@ApiModelProperty("登录账号，唯一")
	@Column(name = "username")
	private String username;

	/**
	 * 真实姓名
	 */
	@ApiModelProperty("真实姓名")
	@Column(name = "realname")
	private String realname;


	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	@Column(name = "user_order")
	private Integer userOrder;



	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	@Column(name = "password")
	//在接口中不返回密码数据
	@JsonIgnore
	private String password;

	/**
	 * md5密码盐
	 */
	@ApiModelProperty("md5密码盐")
	@Column(name = "salt")
	private String salt;

	/**
	 * 头像
	 */
	@ApiModelProperty("头像")
	@Column(name = "avatar")
	private String avatar;



	/**
	 * 性别(0-默认未知,1-男,2-女)
	 */
	@ApiModelProperty("性别(0-默认未知,1-男,2-女)")
	@Column(name = "sex")
	private Integer sex;

	/**
	 * 电子邮件
	 */
	@ApiModelProperty("电子邮件")
	@Column(name = "email")
	private String email;

	/**
	 * 电话
	 */
	@ApiModelProperty("电话")
	@Column(name = "phone")
	private String phone;

	/**
	 * 状态(1-正常,2-冻结)
	 */
	@ApiModelProperty("状态(1-正常,2-冻结)")
	@Column(name = "status")
	private Integer status;


	/**
	 * 工号，唯一键
	 */
	@ApiModelProperty("工号，唯一键")
	@Column(name = "work_no")
	private String workNo;

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
	 * 用户类型，可按业务需要扩展（1普通用户 2管理员...）
	 */
	@ApiModelProperty("用户类型，可按业务需要扩展（1普通用户 2管理员...）")
	@Column(name = "user_type")
	private Integer userType;

	/**
	 * 用于字段扩展，可用json格式
	 */
	@ApiModelProperty("用于字段扩展，可用json格式")
	@Column(name = "ext_data")
	private String extData;

	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	@Column(name = "nickname")
	private String nickname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(Integer userOrder) {
		this.userOrder = userOrder;
	}
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}



	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getExtData() {
		return extData;
	}

	public void setExtData(String extData) {
		this.extData = extData;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
