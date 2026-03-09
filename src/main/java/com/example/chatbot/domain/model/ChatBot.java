package com.example.chatbot.domain.model;

import com.example.chatbot.application.port.out.UserRepository;

import java.util.*;

public class ChatBot {
    private final UserRepository userRepository;

    public ChatBot(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser() {
        return new User();
    }

    public String handleUserMessage(UUID userId, String message) {
        return createUser().handleUserMessage(message).getMessage();
    }
}
