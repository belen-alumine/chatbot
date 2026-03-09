package com.example.chatbot.application.useCase;

import com.example.chatbot.application.port.in.HandleUserMessageInputPort;
import com.example.chatbot.application.port.out.ConversationRepository;
import com.example.chatbot.application.port.out.UserRepository;
import com.example.chatbot.domain.model.Conversation;
import com.example.chatbot.domain.model.ConversationResponse;
import com.example.chatbot.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HandleUserMessageUseCase implements HandleUserMessageInputPort {
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    public HandleUserMessageUseCase(UserRepository userRepository, ConversationRepository conversationRepository) {
        this.userRepository = userRepository;
        this.conversationRepository = conversationRepository;
    }

    @Override
    public User createUser() {
        User user = new User();
        User userSaved = userRepository.save(user);
        System.out.println("Created user: " + userSaved);
        return userSaved;
    }

    @Override
    public String handleUserMessage(UUID userId, String input) {
        System.out.println("handleUserMessage");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ConversationResponse response =
                user.handleUserMessage(input);

        Conversation conversation = user.getCurrentConversation();
        conversationRepository.save(conversation, userId);

        userRepository.save(user);

        return response.getMessage();
    }
}
