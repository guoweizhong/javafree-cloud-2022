package com.javafree.cloud.admin;

import com.javafree.cloud.admin.entity.User;
import com.javafree.cloud.admin.service.UserService;
import com.javafree.cloud.common.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/10/13 13:37
 * @version V1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgUserTest {
    @Autowired
    UserService userService ;
    @Test
    public void testSaveUser(){
        User orgUser=new User();
        orgUser.setRealname("张三");
        orgUser.setUsername("zhangsan6778");
        orgUser.setPassword("12121212");
        userService.saveUser(orgUser);
    }

    @Test
    public void testUpdateUser(){
        User orgUser=userService.getUserById("u1");

        orgUser.setRealname("张三update");
        orgUser.setUsername("zhangsan");
        User tmUser=userService.saveUser(orgUser);
        System.out.println("新用户："+JsonUtils.getJsonStringFromObject(tmUser));
    }

    @Test
    public void testUpdateUser1(){
        User orgUser=new User();
        orgUser.setId("u1");
        orgUser.setRealname("张三update1");
        orgUser.setUsername("zhangsan");
        User tmUser=userService.saveUser(orgUser);
        System.out.println("新用户："+JsonUtils.getJsonStringFromObject(tmUser));
    }
    //@org.junit.jupiter.api.Test
//    void initDbOrgUser(){
//        List<OrgUser> ems= IntStream.rangeClosed(1,200)
//                .mapToObj(i->new OrgUser())
//                .collect(Collectors.toList());
//
//        orgUserDao.saveAll(ems);
//    }


}
