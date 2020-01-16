package com.bootexample.demo.Controller;

import com.bootexample.demo.pojo.WSMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class websocketController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
     @RequestMapping("/")
    public  String sendMessage(){
        return "login.html";
    }
    /**
     * 发送指定用户
     *
     * @param msg WebMessage
     */
    @MessageMapping("/chat")
    public void toOne() {
               simpMessagingTemplate.convertAndSendToUser("1", "/queue/chat", "tsetok");

    }
}
