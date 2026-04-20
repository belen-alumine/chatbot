package com.example.chatbot.infrastructure.adapter.in.rest.dto;

import java.util.List;
import java.util.UUID;

public record NewUserResponse(
        UUID userId,
        List<UUID> conversationId
) {}