package com.example.chatbot.infrastructure.persistance.entity;

import com.example.chatbot.domain.model.ConversationState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "conversarions")
public class ConversationEntity {
    @Id
    @Column(name = "conversation_id")
    private UUID conversationId;
    @Column(name = "user_id")
    private UUID userId; // FK
    @Column(name = "conversation_state")
    private ConversationState conversationState;
    @Column(name = "user_name")
    private String userName;

    public ConversationEntity() {
    }

    public ConversationEntity(UUID conversationId, UUID userId, ConversationState conversationState, String userName) {
        this.conversationId = conversationId;
        this.userId = userId;
        this.conversationState = conversationState;
        this.userName = userName;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public void setConversationState(ConversationState conversationState) {
        this.conversationState = conversationState;
    }

    public void setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
    }

    public UUID getUserId() {
        return userId;
    }

    public ConversationState getConversationState() {
        return conversationState;
    }


    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
