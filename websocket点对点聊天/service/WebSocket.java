package com.bootexample.demo.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
//websocket的服务端 每个客户端链接就会对应一个WebSocket对象.
public class WebSocket {
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    String username="wangwu";
    private Session session;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocket> webSockets=new CopyOnWriteArraySet<>();
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session){//restFul风格的url"/websocket/zhangsan"
        this.session=session;
        webSockets.add(this);
        System.out.println("有新的连接，总数"+webSockets.size());
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        System.out.println("有新的断开，总数"+webSockets.size());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message){
        System.out.println(message);
        send(message);
    }
    /**
     * 实现服务器主动推送
     */
    public void send(String message){
        for (WebSocket webSocket:webSockets){
            try {
                //获取集合中的WebSocket对象并向其发送消息
                webSocket.session.getBasicRemote().sendText(webSocket.username+":"+message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
