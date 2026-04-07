package com.example.chatbot.infrastructure.persistance.adapter;

import com.example.chatbot.application.port.out.ConversationRepository;
import com.example.chatbot.domain.model.Conversation;
import com.example.chatbot.infrastructure.persistance.entity.ConversationEntity;
import com.example.chatbot.infrastructure.persistance.repository.SpringDataConversationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaConversationRepositoryAdapter implements ConversationRepository {
    private final SpringDataConversationRepository springDataConversationRepository;

    public JpaConversationRepositoryAdapter(SpringDataConversationRepository springDataConversationRepository) {
        this.springDataConversationRepository = springDataConversationRepository;
    }

    @Override
    public Conversation save(Conversation conversation, UUID userId) {
        ConversationEntity entity = springDataConversationRepository
                .findById(conversation.getConversationId())
                .map(existingEntity -> {
                    existingEntity.setConversationState(conversation.getConversationState());
                    existingEntity.setUserName(conversation.getUserName());
                    return existingEntity;
                })
                .orElseGet(() -> mapToEntity(conversation, userId));

        springDataConversationRepository.saveAndFlush(entity);
        return conversation;
    }

    private ConversationEntity mapToEntity(Conversation conversation, UUID userId) {
        ConversationEntity entity = new ConversationEntity();
        entity.setConversationId(conversation.getConversationId());
        entity.setUserId(userId);
        entity.setConversationState(conversation.getConversationState());
        entity.setUserName(conversation.getUserName());
        return entity;
    }

    @Override
    public Optional<Conversation> findById(UUID id) {
        return springDataConversationRepository.findById(id)
                .map(this::mapToDomain);
    }

    private Conversation mapToDomain(ConversationEntity entity) {
        return new Conversation(
                entity.getConversationId(),
                entity.getConversationState(),
                entity.getUserName()
        );
    }
}
