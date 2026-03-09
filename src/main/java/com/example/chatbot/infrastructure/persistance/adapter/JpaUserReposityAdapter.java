package com.example.chatbot.infrastructure.persistance.adapter;

import com.example.chatbot.application.port.out.UserRepository;
import com.example.chatbot.domain.model.User;
import com.example.chatbot.infrastructure.persistance.entity.UserEntity;
import com.example.chatbot.infrastructure.persistance.repository.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserReposityAdapter implements UserRepository {

    private final SpringDataUserRepository springUserRepository;

    public JpaUserReposityAdapter(SpringDataUserRepository springUserRepository) {
        this.springUserRepository = springUserRepository;
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
        return new User(entity.getUserId());
    }


}
