package com.javafree.cloud;
/*
 * @Description:系统管理基础功能服务启动入口
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 9:30
 * @version V1.0
 */

import com.javafree.cloud.jpa.JavaFreeRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableOpenApi
//加包的扫描范围，默认有些bean无法加载,解决GlobalExceptionHandler不生效问题
//@SpringBootApplication(scanBasePackages = {"com.javafree.cloud", "com.javafree.cloud.common"})
//@EnableJpaRepositories(repositoryFactoryBeanClass = JavaFreeRepositoryImpl.class,
//basePackages = {"com.javafree.cloud.admin.dao"})
@SpringBootApplication
@EnableDiscoveryClient
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