package com.example.chatbot.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserRequest(
        @NotBlank UUID userId
) {}