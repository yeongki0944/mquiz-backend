package com.mzc.quiz.play.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connect") // SockJS("http://localhost:8080/connect") 경로 등록
                .setAllowedOriginPatterns("*") // CORS 문제 해결
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/quiz"); // stompSend로 전달해온 경로값 지정(controller에서 따로 경로를 붙여주지 않아도 됨)
        config.enableSimpleBroker("/pin", "/queue"); // broker를 통해 pub/sub 관리
    }
}
