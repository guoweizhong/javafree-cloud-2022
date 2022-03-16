package com.javafree.cloud.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
/**
 * @Description:    用户表
 * @Database:   表名为 sys_org_User
 */

@Entity
@Table(name ="sys_org_User")
@ApiModel(value = " User对象 ", description = "用户表")
@DynamicUpdate //只更新修改过且有值的字段
@DynamicInsert//如果这个字段的值是null就不会加入到insert语句中
public class User  implements Serializable{

	private static final Long serialVersionUID = 5168186435465409123L;

	/**
	 * 主键id
	 */
	@ApiModelProperty("主键id")
  	@Id
	@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
	//自动生成主键，如果不指定，新增时则需要手动设置ID
	@GeneratedValue(generator = "jpa-uuid")
	@Column(name = "id",length = 32)
	@Length(min=1,max = 32,message = "长度范围在1-32之间")
	private String id;

	/**
	 * 登录账号
	 */
	@ApiModelProperty("登录账号")
  	@Column(name = "username")
	@NotBlank(message = "用户登录名不能为空")
	private String username;

	/**
	 * 真实姓名
	 */
	@ApiModelProperty("真实姓名")
  	@Column(name = "realname")
	@NotBlank(message = "用户真实姓名不能为空")
	private String realname;

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
	 * 生日
	 */
	@ApiModelProperty("生日")
  	@Column(name = "birthday")
	private Date birthday;

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
	@Email(message = "邮箱格式")
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
	 * 删除状态(0-正常,1-已删除)
	 */
	@ApiModelProperty("删除状态(0-正常,1-已删除)")
  	@Column(name = "del_flag")
	private Integer del_flag;

	/**
	 * 工号，唯一键
	 */
	@ApiModelProperty("工号，唯一键")
  	@Column(name = "work_no")
	private String work_no;

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
	 * 用户类型，可按业务需要扩展（1普通成员 2管理员...）
	 */
	@ApiModelProperty("用户类型，可按业务需要扩展（1普通成员 2管理员...）")
  	@Column(name = "User_type")
	private Integer User_type;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public Integer getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(Integer del_flag) {
		this.del_flag = del_flag;
	}

	public String getWork_no() {
		return work_no;
	}

	public void setWork_no(String work_no) {
		this.work_no = work_no;
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

	public Integer getUser_type() {
		return User_type;
	}

	public void setUser_type(Integer User_type) {
		this.User_type = User_type;
	}

	public String getExt_data() {
		return ext_data;
	}

	public void setExt_data(String ext_data) {
		this.ext_data = ext_data;
	}

}
