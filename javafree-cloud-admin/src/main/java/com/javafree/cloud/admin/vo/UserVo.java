package com.javafree.cloud.admin.vo;


import com.javafree.cloud.admin.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
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


@Schema(name = " UserVo对象 ", description = "UserVo对象,用于页面传参")
@DynamicUpdate //设置为true,表示Update对象的时候,生成动态的Update语句,如果这个字段的值是null就不会加入到Update语句当中.默认false
@DynamicInsert  //设置为true,表示insert对象的时候,生成动态的insert语句,如果这个字段的值是null就不会加入到insert语句当中.默认false
public class UserVo extends User {
    @Schema(name = "password", description = "密码")
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }


}
