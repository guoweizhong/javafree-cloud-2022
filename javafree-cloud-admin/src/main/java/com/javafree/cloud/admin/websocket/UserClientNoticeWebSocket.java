package com.javafree.cloud.admin.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version V1.0
 * @Description:
 * 通过websocket 客户端 连接测试
 * 地址为ws://localhost:8080/websocket/user1
 * @Author gwz  gwz126@126.com
 * @Date 2022/5/11 14:56
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{userid}") //此注解相当于设置访问URL
public class UserClientNoticeWebSocket {
  static final ConcurrentHashMap<String, List<WebSocketClient>> webSocketClientMap= new ConcurrentHashMap<>();

  /**
   * 连接建立成功时触发，绑定参数
   * @param session 与某个客户端的连接会话，需要通过它来给客户端发送数据
   * @param userid 用户ID
   */
  @OnOpen
  public void onOpen(Session session,@PathParam("userid") String userid){

    WebSocketClient client = new WebSocketClient();
    client.setSession(session);
    client.setUri(session.getRequestURI().toString());

    List<WebSocketClient> webSocketClientList = webSocketClientMap.get(userid);
    if(webSocketClientList == null){
      webSocketClientList = new ArrayList<>();
    }
    webSocketClientList.add(client);
    webSocketClientMap.put(userid, webSocketClientList);
  }

  /**
   * 连接关闭时触发，注意不能向客户端发送消息了
   * @param userid
   */
  @OnClose
  public void onClose(@PathParam("userid") String userid){
    webSocketClientMap.remove(userid);
  }

  /**
   * 通信发生错误时触发
   * @param session
   * @param error
   */
  @OnError
  public void onError(Session session, Throwable error) {
    System.out.println("发生错误");
    error.printStackTrace();
  }

  /**
   * 向客户端发送消息
   * @param userid
   * @param message
   */
  public static void sendMessage(String userid,String message){
    try {
      List<WebSocketClient> webSocketClientList = webSocketClientMap.get(userid);
      if(webSocketClientList!=null){
        for(WebSocketClient webSocketServer:webSocketClientList){
          webSocketServer.getSession().getBasicRemote().sendText(message);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
  }
}
