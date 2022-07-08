package com.javafree.cloud.admin.websocket;

import javax.websocket.Session;

/**
 * @version V1.0
 * @Description:  WebSocket客户端连接信息
 * @Author gwz  gwz126@126.com
 * @Date 2022/5/11 15:04
 */

public class WebSocketClient {
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //客户端连接的uri
    private String uri;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
