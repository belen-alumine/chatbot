package com.example.chatbot.infrastructure.adapter.in.rest.controller;

import com.example.chatbot.application.port.in.HandleUserMessageInputPort;
import com.example.chatbot.application.port.out.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final HandleUserMessageInputPort chatUseCase;
    private final UserRepository userRepository;

    public UserController(HandleUserMessageInputPort chatUseCase, UserRepository userRepository) {
        this.chatUseCase = chatUseCase;
        this.userRepository = userRepository;
    }
}
