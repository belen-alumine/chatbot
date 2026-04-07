package com.example.chatbot.domain.model;

import java.util.UUID;

public class Conversation {
    private final UUID conversationId;
    private ConversationState conversationState;
    private String userName;

    public Conversation() {
        this.conversationId = UUID.randomUUID();
        this.conversationState = ConversationState.NEW;
        System.out.println("Conversation was created: " + conversationId + ", with State:  " + conversationState);
    }

    public Conversation(UUID conversationId, ConversationState conversationState, String userName) {
        this.conversationId = conversationId;
        this.conversationState = conversationState;
        this.userName = userName;
    }

    public ConversationResponse handleUserMessage(String message) {
        System.out.println("Conversation received: " + conversationId + ", Message: " + message + "Conversation State this: " + this.conversationState);
        ConversationState convState = this.conversationState;
        switch (convState) {
            case NEW:
                System.out.println("Is a NEW conversation    // switch de Conversation");
                this.setConversationState(ConversationState.WAITING_FOR_NAME);
                System.out.println("Conversation received: " + this.conversationId +"Conversation State this: " + this.conversationState);
                return new ConversationResponse("Hola, cuál es tu nombre");
            case WAITING_FOR_NAME:
                conversationState = ConversationState.IN_PROGRESS;
                userName = message;
                return new ConversationResponse("Hola, " + message);
            case IN_PROGRESS:
                if (message.equals("Chau")) { conversationState = ConversationState.CLOSE;}
                userName = message;
                return new ConversationResponse("Hola, ");
            default:
                return new ConversationResponse("Error.");
        }
    }

    private void setConversationState(ConversationState conversationState) {
        this.conversationState = conversationState;
    }


    public UUID getConversationId() {
        return conversationId;
    }

    public ConversationState getConversationState() {
        return conversationState;
    }

    public String getUserName() {
        return userName;
    }
}
