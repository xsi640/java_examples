package com.suyang;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class TestWebSocketConfig  extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//websocket前缀过滤
		//这个前缀的地址提供订阅服务
		//前端，监听"/topic/xxx"的消息
		//后端使用@SendTo("/topic/xxx")或SimpMessagingTemplate.convertAndSend("/topic/xxx",xxx)发布消息
		registry.enableSimpleBroker("/topic");
		//设置websocket的应用前缀，controller是这个前缀，提供发布服务
		//前端，可以发布消息给"/test-websocket/xxx"
		//后端，监听@MessageMapping("xxx")的消息
		registry.setApplicationDestinationPrefixes("/publish");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//websocket连接的地址
		registry.addEndpoint("/my-websocket").withSockJS();
	}

}
