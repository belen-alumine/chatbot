package com.example.chatbot.infrastructure.persistance.adapter;

import com.example.chatbot.application.port.out.UserRepository;
import com.example.chatbot.domain.model.Conversation;
import com.example.chatbot.domain.model.User;
import com.example.chatbot.infrastructure.persistance.entity.ConversationEntity;
import com.example.chatbot.infrastructure.persistance.entity.UserEntity;
import com.example.chatbot.infrastructure.persistance.repository.SpringDataConversationRepository;
import com.example.chatbot.infrastructure.persistance.repository.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserReposityAdapter implements UserRepository {

    private final SpringDataUserRepository springUserRepository;
    private final SpringDataConversationRepository springConversationRepository;

    public JpaUserReposityAdapter(SpringDataUserRepository springUserRepository, SpringDataConversationRepository springConversationRepository) {
        this.springUserRepository = springUserRepository;
        this.springConversationRepository = springConversationRepository;
    }

    @Override
    public User save(User user) {

        UserEntity entity = toEntity(user);

        UserEntity savedEntity = springUserRepository.save(entity);

        return toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return springUserRepository.findById(id)
                .map(this::toDomain);
    }

    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setUserId(user.getUserId());
        return entity;
    }

    private User toDomain(UserEntity entity) {
        User user = new User(entity.getUserId());

        List<ConversationEntity> conversationEntities =
                springConversationRepository.findByUserId(entity.getUserId());

        for (ConversationEntity ce : conversationEntities) {
            Conversation conversation = mapToDomain(ce);
            user.addConversation(conversation);
        }

        return user;
        //return new User(entity.getUserId());
    }

    private Conversation mapToDomain(ConversationEntity entity) {
        return new Conversation(
                entity.getConversationId(),
                entity.getConversationState(),
                entity.getUserName()
        );
    }


}