package com.javafree.cloud.demo.controller;

import com.javafree.cloud.demo.DemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/10/15 11:26
 * @version V1.0
 */
@Slf4j
@RestController
public class DemoController {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello(Authentication authentication){
        final String body="Hello"+authentication.getName();
        return  ResponseEntity.ok(body);
    }
}
