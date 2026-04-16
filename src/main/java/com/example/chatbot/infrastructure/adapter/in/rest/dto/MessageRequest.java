package com.example.chatbot.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record MessageRequest(
        UUID userId,
        @NotBlank String message
) {}