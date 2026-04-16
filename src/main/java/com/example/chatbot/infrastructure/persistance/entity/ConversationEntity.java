package com.example.chatbot.infrastructure.persistance.entity;

import com.example.chatbot.domain.model.ConversationState;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "conversations")
public class ConversationEntity {
    @Id
    @Column(name = "conversation_id")
    private UUID conversationId;

    // EN LUGAR DE: private UUID userId;
    // USA ESTO:
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id", referencedColumnName = "user_id")
    //private UserEntity userId;

    @Column(name = "user_id")
    private UUID userId; // FK

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
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

    public UUID getId() {
        return conversationId;
    }
}
