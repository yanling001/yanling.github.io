package com.bootexample.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


@Configuration
@EnableWebSocketMessageBroker
//webSocket的配置类
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    //注册websocket
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //客户端与服务器端建立连接的点，允许使用sockJs方式访问，允许跨域
        stompEndpointRegistry.addEndpoint("/socket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        //订阅Broker名称
        messageBrokerRegistry.enableSimpleBroker("/user","/queue");
        //全局使用的订阅前缀(客户端订阅路径上会体现出来)
        messageBrokerRegistry.setApplicationDestinationPrefixes("/app");
        //点对点使用的订阅前缀(客户端订阅路径上会体现出来)，不设置的话，默认也是/user/
        //注意:enableSimpleBroker方法里的某个参数路径必须和该方法的路径要一样，不然指定用户发送消息将会失败
        //messageBrokerRegistry.setUserDestinationPrefix("/user/");
    }
}
