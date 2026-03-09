package com.example.chatbot.application.port.in;


import com.example.chatbot.domain.model.User;

import java.util.UUID;

public interface HandleUserMessageInputPort {
    User createUser();
    String handleUserMessage(UUID userId, String input);
}
