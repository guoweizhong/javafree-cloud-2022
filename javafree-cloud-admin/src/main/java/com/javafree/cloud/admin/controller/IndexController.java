package com.javafree.cloud.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/1/10 17:48
 */
@Controller
public class IndexController {

    @GetMapping(value = {"/","index.html"})
    public String indexPage(){

        return "index";
    }
}
