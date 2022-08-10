package com.javafree.cloud.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
  @Resource(name="cacheManager")
  CacheManager cacheManager;

  @RequestMapping("/get")
  public boolean get() {
    String strVal=applicationContext.getEnvironment().getProperty("test.useLocalCache");
    boolean useLocalCache =Boolean.valueOf(strVal);

    return useLocalCache;
  }

  @RequestMapping("/getCache")
  public boolean getCache() {
    List<String> caches= (List<String>) cacheManager.getCacheNames();
    String strVal=applicationContext.getEnvironment().getProperty("test.useLocalCache");
    boolean useLocalCache =Boolean.valueOf(strVal);

    return useLocalCache;
  }
}
