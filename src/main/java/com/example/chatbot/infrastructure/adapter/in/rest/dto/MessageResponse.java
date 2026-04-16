package com.example.chatbot.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record MessageResponse(
        UUID userId,
        UUID conversationId,
        String state,
        String response
) {}
