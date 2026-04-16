package com.example.chatbot.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsumerTest {

    @Test
    void shouldCreateConversationOnFirstMessage() {
        User user = new User();

        ConversationResponse response = user.handleUserMessage("hola");
        Conversation currentConversation = user.getCurrentConversation();

        assertNotNull(currentConversation.getConversationId());
        assertEquals(ConversationState.WAITING_FOR_NAME, currentConversation.getConversationState());
        assertTrue(response.getMessage().contains("hablando"));
    }

    @Test
    void shouldAdvanceConversationStatesUntilClose() {
        User user = new User();

        user.handleUserMessage("hola");
        ConversationResponse nameResponse = user.handleUserMessage("Ana");
        assertTrue(nameResponse.getMessage().contains("Ana"));
        assertEquals(ConversationState.IN_PROGRESS, user.getCurrentConversation().getConversationState());

        ConversationResponse closeResponse = user.handleUserMessage("chau");
        assertEquals(ConversationState.CLOSE, user.getCurrentConversation().getConversationState());
        assertTrue(closeResponse.getMessage().contains("nos vemos pronto"));
    }
}
