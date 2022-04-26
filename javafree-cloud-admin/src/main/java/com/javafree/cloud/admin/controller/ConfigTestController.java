package com.javafree.cloud.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2022/3/19 9:26
 */
@RestController
@RequestMapping("/test_config")
@RefreshScope
public class ConfigTestController {

  @Value("${test.useLocalCache:false}")
  private boolean useLocalCache;

  @RequestMapping("/get")
  public boolean get() {
    return useLocalCache;
  }
}
