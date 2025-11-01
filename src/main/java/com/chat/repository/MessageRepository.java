package com.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.model.ChatMessage;

public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
	List<ChatMessage> findByReceiver(String receiver);
}
