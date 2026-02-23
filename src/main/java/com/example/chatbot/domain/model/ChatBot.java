package com.example.chatbot.domain.model;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChatBot {
    //private final chatId = UUID.randomUUID();
    private final Map<UUID, User> users = new HashMap<>();
    //private UUID chatId;
    //private final String chatName;
    //private final Map<UUID, User> users;
/*
    public ChatBot() {
        this.chatId = UUID.randomUUID();
        //this.chatName = chatName;
        this.users = new HashMap<>();
        System.out.println("ChatBot created: " + chatId);
    }
*/
    public User createUser() {
        User user = new User();
        users.put(user.getUserId(), user);
        //System.out.println("User added: " + chatId + ". Number of users: " + users.size());
        System.out.println(user.getUserId() + " has been created. Number of users: " + users.size());
        return user;
    }

    public String handleUserMessage(UUID userId, String message) {
        User user = users.get(userId);
        System.out.println("User " + userId + " received message: " + message);
        System.out.println("Users in map: " + users.size());

        if (user == null) {
            return "Error. User not found.";
        }
        ConversationResponse response = user.handleUserMessage(message);
        return response.getMessage();
    }

    //public String getChatName() {
    //    return chatName;
    //}

    //public UUID getChatId() {
    //    return chatId;
    //}

}
