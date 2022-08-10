package com.javafree.cloud.admin.controller;

import com.javafree.cloud.admin.websocket.UserClientNoticeWebSocket;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @Description:
 * 当系统上线后，用Nginx部署，并用域名连接时就会失败。此时只需要在Nginx配置文件里加入一些配置即可。配置如下
 * server{
 *   listen 80;
 *   server_name test.com  www.test.com;
 *
 *   # 访问WebSocket
 *   location /web_socket{
 *     proxy_pass http://47.*.27.1:8002;
 *     proxy_set_header Host $host;
 *     #启用支持websocket连接
 *     proxy_set_header Upgrade $http_upgrade;
 *     proxy_set_header Connection "upgrade";
 *   }
 * }
 * @Author gwz  gwz126@126.com
 * @Date 2022/5/11 15:10
 */
@Tag(name = "发送websockt消息", description ="发送websockt消息")
@RestController
public class UsreClientWebSocketController {

  @Operation(summary = "发送websockt消息", description = "发送websockt消息")

  @GetMapping(value = "/sent_message/{userid}/{message}")
  public void test(@PathVariable(value = "userid")  String userid,@PathVariable(value = "message")  String message){
    UserClientNoticeWebSocket.sendMessage(userid,message);
  }
}
