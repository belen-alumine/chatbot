package com.example.chatbot.infrastructure.adapter.in.rest.controller;

import com.example.chatbot.application.port.in.HandleUserMessageInputPort;
import com.example.chatbot.application.port.out.UserRepository;
import com.example.chatbot.domain.model.Conversation;
import com.example.chatbot.domain.model.User;
import com.example.chatbot.infrastructure.adapter.in.rest.dto.NewUserResponse;
import com.example.chatbot.infrastructure.adapter.in.rest.dto.UserRequest;
import com.example.chatbot.infrastructure.adapter.in.rest.dto.UserResponse;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final HandleUserMessageInputPort chatUseCase;
    private final UserRepository userRepository;

    public UserController(HandleUserMessageInputPort chatUseCase, UserRepository userRepository) {
        this.chatUseCase = chatUseCase;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(
                user.getUserId(),
                getConversationIds(user.getConversations())
        );
    }

    @PostMapping("/new")
    public NewUserResponse createUser() {
        User user = new User();
        return new NewUserResponse(
                user.getUserId(),
                getConversationIds(user.getConversations())
        );
    }

    public List<UUID> getConversationIds(List<Conversation> conversations) {
        List<UUID> conversationIds = new ArrayList<>();
        for (Conversation conversation : conversations) {
            conversationIds.add(conversation.getConversationId());
        }
        return conversationIds;
    }
}
