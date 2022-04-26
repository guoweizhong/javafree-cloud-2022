package com.javafree.cloud.undertow;

/**
 *
 */

import io.undertow.server.DefaultByteBufferPool;
import io.undertow.websockets.jsr.WebSocketDeploymentInfo;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/*
 * @Description:系统管理基础功能服务启动入口
 * @Author gwz  gwz126@126.com
 * @Date 2021/8/26 9:30
 * @version V1.0
 */

/**
 *  解决 undertow警告
 *   WARN  io.undertow.websockets.jsr:68 - UT026010: Buffer pool was not set on WebSocketDeploymentInfo, the default pool will be used
 *   这个警告不影响使用,为了完善
 *  根据官方文档,自定义配置
 */

@Component
public class CustomizationBean implements WebServerFactoryCustomizer<UndertowServletWebServerFactory> {
    @Override
    public void customize(UndertowServletWebServerFactory factory) {
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            WebSocketDeploymentInfo webSocketDeploymentInfo = new WebSocketDeploymentInfo();
            webSocketDeploymentInfo.setBuffers(new DefaultByteBufferPool(false, 1024));
            deploymentInfo.addServletContextAttribute("io.undertow.websockets.jsr.WebSocketDeploymentInfo", webSocketDeploymentInfo);
        });
    }
}
