package com.javafree.cloud;
/*
 * @Description:系统管理基础功能服务启动入口
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 9:30
 * @version V1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
//加包的扫描范围，默认有些bean无法加载,解决GlobalExceptionHandler不生效问题
//@SpringBootApplication(scanBasePackages = {"com.javafree.cloud", "com.javafree.cloud.common"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class JavafreeAdminApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(JavafreeAdminApplication.class, args);

        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        port=port==null?"":port;
        path=path==null?"":path;
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Javafree-admin is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n" +
                "----------------------------------------------------------");
    }
}