package com.example.chatbot.application.port.out;

import com.example.chatbot.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
}
