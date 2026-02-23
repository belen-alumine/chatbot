package com.example.chatbot.domain.model;


import java.util.UUID;

public class Conversation {

        private final UUID conversationId;
        private ConversationState conversationState;
        private String userName;

        public Conversation() {
            this.conversationId = UUID.randomUUID();
            this.conversationState = ConversationState.NEW;
            System.out.println("Conversation was created: " + conversationId);
        }

        public ConversationResponse handleUserMessage(String message) {
            switch (conversationState) {
                case NEW:
                    System.out.println("Is a NEW conversation    // switch de Conversation");
                    conversationState = ConversationState.WAINTING_FOR_NAME;
                    return new ConversationResponse("Hola, cuál es tu nombre");
                case WAINTING_FOR_NAME:
                    conversationState = ConversationState.IN_PROGRESS;
                    userName = message;
                    return new ConversationResponse("Hola, " + message);
                default:
                    return new ConversationResponse("Error.");
            }
        }

        public UUID getConversationId() {
            return conversationId;
        }
}
