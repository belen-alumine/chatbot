package com.example.chatbot.application.port.in;


import java.util.UUID;

public interface HandleUserMessageInputPort {
    String handleUserMessage(UUID userId, String input);
}
