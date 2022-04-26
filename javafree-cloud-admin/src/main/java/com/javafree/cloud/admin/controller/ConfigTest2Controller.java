package com.javafree.cloud.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/3/19 9:26
 */
@RestController
@RequestMapping("/test2_config")
public class ConfigTest2Controller {
  @Autowired
  private ConfigurableApplicationContext applicationContext;


  @RequestMapping("/get")
  public boolean get() {
    String strVal=applicationContext.getEnvironment().getProperty("test.useLocalCache");
    boolean useLocalCache =Boolean.valueOf(strVal);

    return useLocalCache;
  }
}
