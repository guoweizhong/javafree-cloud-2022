package com.javafree.cloud.demo;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/10/15 11:33
 * @version V1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args){
        SpringApplication.run(DemoApplication.class,args);
    }
}
