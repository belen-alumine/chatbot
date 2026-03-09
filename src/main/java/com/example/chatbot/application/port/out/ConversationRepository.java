package com.example.chatbot.application.port.out;

import com.example.chatbot.domain.model.Conversation;

import java.util.Optional;
import java.util.UUID;

public interface ConversationRepository {
    Conversation save(Conversation conversation, UUID userId);
    Optional<Conversation> findById(UUID id);
}
