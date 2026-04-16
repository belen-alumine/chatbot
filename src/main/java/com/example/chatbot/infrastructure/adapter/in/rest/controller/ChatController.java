package com.example.chatbot.infrastructure.adapter.in.rest.controller;

import com.example.chatbot.application.port.in.HandleUserMessageInputPort;
import com.example.chatbot.application.port.out.UserRepository;
import com.example.chatbot.domain.model.Conversation;
import com.example.chatbot.domain.model.User;
import com.example.chatbot.infrastructure.adapter.in.rest.dto.MessageRequest;
import com.example.chatbot.infrastructure.adapter.in.rest.dto.MessageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final HandleUserMessageInputPort chatUseCase;
    private final UserRepository userRepository;

    public ChatController(HandleUserMessageInputPort chatUseCase, UserRepository userRepository) {
        this.chatUseCase = chatUseCase;
        this.userRepository = userRepository;
    }

    @PostMapping
    public MessageResponse sendMessage(@RequestBody MessageRequest request) {
        String reply = chatUseCase.handleUserMessage(request.userId(), request.message());

        User user = userRepository.findById(request.userId())
                .orElseThrow();
        Conversation conv = user.getCurrentConversation();

        return new MessageResponse(
                request.userId(),
                conv.getConversationId(),
                conv.getConversationState().name(),
                reply
        );
    }
}
