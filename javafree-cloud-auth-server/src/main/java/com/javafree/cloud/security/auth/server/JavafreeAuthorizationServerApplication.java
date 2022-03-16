package com.javafree.cloud.security.auth.server;
/*
 * @Description:
 * @Author gwz  gwz126@126.com
 * @Date 2021/9/11 15:21
 * @version V1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class JavafreeAuthorizationServerApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(JavafreeAuthorizationServerApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Javafree-AuthorizationServer is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + "/\n\t" +
                "External: \thttp://" + ip + ":" + port +  "/\n" +
                "----------------------------------------------------------");

    }
}


