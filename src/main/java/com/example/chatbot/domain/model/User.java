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
        System.out.println("User created: " + userId);
    }

    public Conversation startNewConversation() {
        Conversation conversation = new Conversation();
        conversations.add(conversation);
        return conversation;
    }

    public ConversationResponse handleUserMessage(String message) {
        return getCurrentConversation().handleUserMessage(message);
    }

    public Conversation getCurrentConversation() {
        if (conversations.isEmpty()) {
            return startNewConversation();
        }
        return conversations.getLast();
    }

    public UUID getUserId() {
        return userId;
    }

/*
    private UUID userId;
    List<Conversation> conversations;
    String userMessage;

    public User() {
        UUID userID = UUID.randomUUID();
        conversations = new ArrayList<Conversation>();
        System.out.println("User created with userId: " + userID);
    }

    public ConversationResponse initializeConversation(String userMessage, UUID chatId) {
        Conversation newConversation = new Conversation(userMessage, chatId);

        /// String randomMsg = traer mensaje desde json de mensajes de bienvenida
        //String randomMsg = "Hola";

        /// Implementar lógica de inicio de conversación.
        ConversationResponse response = new ConversationResponse(userMessage, newConversation);
        //response.setMessage(randomMsg);
        conversations.add(newConversation);

        return response;
    }


    //// GETTERS AND SETTERS ////

    public UUID getUserId() {
        return userId;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    public Conversation getLastConversation() {
        return conversations.getFirst();
    }

    public String getUserMessage() {
        return userMessage;
    }

 */
}
