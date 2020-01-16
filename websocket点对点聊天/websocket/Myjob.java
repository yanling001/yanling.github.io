package com.bootexample.demo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class Myjob    {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
  //  @Scheduled(fixedRate = 1000)
    public void sendmassege(){
        System.out.println("服务端发送");
        simpMessagingTemplate.convertAndSend("/user/chat","你好");
    }
}
