package com.example.chatbot.infrastructure.persistance.repository;

import com.example.chatbot.infrastructure.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "select * from users where user_id = :id limit 1", nativeQuery = true)
    Optional<UserEntity> findFirstByUserId(@Param("id") UUID id);

    @Query(value = "select exists(select 1 from users where user_id = :id)", nativeQuery = true)
    boolean existsByUserId(@Param("id") UUID id);
}
