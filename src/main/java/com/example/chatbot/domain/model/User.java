package com.example.chatbot.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID userId;
    private final List<Conversation> conversations;

    public User() {
        this.userId = UUID.randomUUID();
        this.conversations = new ArrayList<>();
        System.out.println("User created: " + this.userId);
    }

    public User(UUID userId) {
        this.userId = userId;
        this.conversations = new ArrayList<>();
    }

    public Conversation startNewConversation() {
        Conversation conversation = new Conversation();
        conversations.add(conversation);
        System.out.println("Conversation started: " + conversation.getConversationId());
        return conversation;
    }

    public ConversationResponse handleUserMessage(String message) {
        return getCurrentConversation().handleUserMessage(message);
    }

    public Conversation getCurrentConversation() {
        if (conversations.isEmpty()) {
            System.out.println("Conversation list is empty");
            return startNewConversation();
        }
        System.out.println("Current conversation: " + conversations.getLast().getConversationId());
        return conversations.getLast();
    }

    public UUID getUserId() {
        return userId;
    }

    public void addConversation(Conversation conversation) {
        conversations.add(conversation);
    }

    public List<Conversation> getConversations() {
        return conversations;
    }
}
