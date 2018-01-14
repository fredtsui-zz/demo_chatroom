package com.example.demo;

import org.kurento.client.KurentoClient;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableWebSocket
public class DemoChatroomApplication implements WebSocketConfigurer{
	
	@Bean
	public UserRegistry registry() {
	    return new UserRegistry();
	}

	@Bean
	public RoomManager roomManager() {
	    return new RoomManager();
	}

	@Bean
	public ChatRoomHandler groupCallHandler() {
	    return new ChatRoomHandler();
	}

	@Bean
	public KurentoClient kurentoClient() {
	    return KurentoClient.create("ws://54.173.184.153:8888/kurento");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoChatroomApplication.class, args);
	}
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
	    registry.addHandler(groupCallHandler(), "/groupcall");
	}
}
