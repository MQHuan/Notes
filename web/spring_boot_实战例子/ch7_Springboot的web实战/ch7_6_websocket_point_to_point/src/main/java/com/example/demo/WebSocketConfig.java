package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker //开启使用STOMP协议来传输基于代理(message broker)的消息这时控制器支持使用@MessageMapping, 就像@RequestMapping一样
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {// 注册STOMP协议的节点(endpoint), 并映射的指定的URL
        stompEndpointRegistry.addEndpoint("/endpointWisely").withSockJS();// 注册一个STOMP的endpoint, 并指定使用SockJS协议
        stompEndpointRegistry.addEndpoint("endpointChat").withSockJS();// 注册一个名为/endpointChat的endpointChat
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(Message Broker)
        registry.enableSimpleBroker("/queue","/topic"); // 点对点式应增加一个/queue消息代理
    }
}

