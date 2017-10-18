package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;// 通过SimpMessagingTemplate 向浏览器发送消息

    @MessageMapping("/chat") // 当浏览器向服务器发送请求时，通过@MessageMapping映射/chat，类似于@RequestMapping
    public void handleChat(Principal principal, String msg) {//在Spring MVC中，可以直接在参数中获得principal,principal中包含当前用户的信息
        if (principal.getName().equals("mai")) {
            messagingTemplate.convertAndSendToUser("demi",
                    "/queue/notifications", principal.getName()+"-send:"
                            +msg);//通过messagingTemplate.convertAndSendToUser向用户发送消息，第一个参数是接收消息的用户，第二个是浏览器订阅的地址，第三个是消息的本身
        } else {
            messagingTemplate.convertAndSendToUser("mai",
                    "/queue/notifications", principal.getName()+"-send:"
            +msg);
        }
    }
}
