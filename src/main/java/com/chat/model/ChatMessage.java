package com.chat.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String sender;
	private String content;
	private String receiver;
	private LocalDateTime timestamp = LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public ChatMessage() {
	}

	public ChatMessage(Long id, String sender, String content, String receiver, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.sender = sender;
		this.content = content;
		this.receiver = receiver;
		this.timestamp = timestamp;
	}

}
