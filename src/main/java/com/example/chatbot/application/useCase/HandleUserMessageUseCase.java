package com.example.chatbot.application.useCase;

import com.example.chatbot.application.port.in.HandleUserMessageInputPort;
import com.example.chatbot.domain.model.ChatBot;
import com.example.chatbot.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HandleUserMessageUseCase implements HandleUserMessageInputPort {
    private final ChatBot chatBot;

    public HandleUserMessageUseCase(ChatBot chatBot) {
        this.chatBot = chatBot;
    }

    @Override
    public String handleUserMessage(UUID userId, String input) {
        return chatBot.handleUserMessage(userId, input);
    }
}
