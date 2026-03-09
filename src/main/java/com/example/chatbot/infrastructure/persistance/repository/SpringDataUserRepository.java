package com.example.chatbot.infrastructure.persistance.repository;

import com.example.chatbot.infrastructure.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {
}
