package com.example.chatbot.infrastructure.persistance.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID userId;

    public UserEntity() {
    }

    public UserEntity(UUID id) {
        this.userId = id;
    }

    public UUID getId() {
        return userId;
    }

    public void setUserId(UUID id) {
        this.userId = id;
    }

    public UUID getUserId() {
        return userId;
    }
}
