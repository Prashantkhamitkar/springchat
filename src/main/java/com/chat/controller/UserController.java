package com.chat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.model.User;
import com.chat.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/login")
	public User login(@RequestBody Map<String, String> payload) {
		String username = payload.get("username");
		return userRepository.findByUsername(username).orElseGet(() -> userRepository.save(new User(null, username)));
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
