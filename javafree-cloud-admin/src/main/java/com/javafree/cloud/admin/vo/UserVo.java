package com.javafree.cloud.admin.vo;


import com.javafree.cloud.admin.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 由于User 对象 password字段设置了@JsonIgnore，
 * 所以如果从前端传的User对象，会删除掉password字段值，但有些接口需要前端传password字段值，
 * 所以用DeptVo 来保留password
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/12/3 14:28
 */


@ApiModel(value = " UserVo对象 ", description = "UserVo对象,用于页面传参")
@DynamicUpdate
@DynamicInsert
public class UserVo extends User {

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @ApiModelProperty("密码")
    private String password;
}
