package com.chatapp.chatapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.chatapp.chatapp.controller.ChatMessage;
import com.chatapp.chatapp.controller.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListnear {
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleWebsocketDisconnectEventListnear(SessionDisconnectEvent event) 
    {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) headerAccessor.getSessionAttributes().get("username");
        if(userName != null) {
            log.info("User disconnected");
            ChatMessage chatMessage =  ChatMessage.builder()
                .type(MessageType.LEAVE)
                .sender("username")
                .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
