package com.example.chatbot.domain.model;

import java.util.UUID;

public interface NewConversation {
    //public void newConversation(ConversationState conversationState, UUID conversationId);

    public void setConversationState(ConversationState conversationState);
    public ConversationState getConversationState();
    public void setConversationId(UUID conversationId);
    public UUID getConversationId();
    public String getUserMessage();

}
