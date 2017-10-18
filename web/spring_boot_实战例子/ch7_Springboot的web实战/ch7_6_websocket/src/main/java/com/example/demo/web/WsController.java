package com.example.demo.web;

import com.example.demo.domain.WiselyMessage;
import com.example.demo.domain.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

    @MessageMapping("/welcome") // 当浏览器向服务器发送请求时，通过@MessageMapping映射/welcome这个地址，类似于@RequestMapping
    @SendTo("/topic/getResponse") // 当服务器端有消息时，会对订阅啦@SendTo中的路径的浏览器发送消息。
    public WiselyResponse say(WiselyMessage message) throws Exception {
        Thread.sleep(3000);
        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }
}
