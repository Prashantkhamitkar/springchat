package com.chat.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.chat.model.ChatMessage;
import com.chat.model.User;
import com.chat.repository.MessageRepository;
import com.chat.repository.UserRepository;

@Controller
public class ChatController {
	private final SimpMessagingTemplate messagingTemplate;
	private final MessageRepository repository;
@Autowired
	public UserRepository repo;
	public ChatController(SimpMessagingTemplate messagingTemplate, MessageRepository repository) {
		this.messagingTemplate = messagingTemplate;
		this.repository = repository;
	}
	@MessageMapping("/sendMessage")
    public void sendMessage(ChatMessage chatMessage) {
        // Send to receiver
        messagingTemplate.convertAndSendToUser(
            chatMessage.getReceiver(), "/queue/messages", chatMessage
        );
        // Optionally send to sender too (if you want it echoed back)
        messagingTemplate.convertAndSendToUser(
            chatMessage.getSender(), "/queue/messages", chatMessage
        );
    }

    // When user connects or disconnects, broadcast updated user list
    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        broadcastUserList();
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        broadcastUserList();
    }

    private void broadcastUserList() {
        List<User> allUsers = repo.findAll();
        messagingTemplate.convertAndSend("/topic/users", allUsers);
    }

}
