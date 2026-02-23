package com.example.chatbot.domain.model;

public class ConversationResponse {
    private final String message;

    public ConversationResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
