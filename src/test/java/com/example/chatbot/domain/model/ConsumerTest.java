package com.example.chatbot.domain.model;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ConsumerTest {

    private static final Logger log =
            LoggerFactory.getLogger(ConsumerTest.class);

    @Test
    void initializeConversationTest() {
        /*
        log.info("************** Start test: initializeConversationTest **************");

        // CASE: user talk for FIRST time. For now, the bot recive an input and create a user and conversation, (method createUser)
        // Inicializar bot
        ChatBot botcito = new ChatBot("Botcito");

        // Inicializar cliente
        User user = botcito.createUser("Hello!");

        assertNotNull(user.getConversations(), "Conversations list should not be null");
        assertEquals(1, user.getConversations().size(), "User should have one conversation");

        Conversation conversation = user.getLastConversation();
        assertNotNull(conversation, "Last conversation should not be null");

        assertNotNull(conversation.getConversationId(), "Conversation ID should not be null");
        assertNotNull(conversation.getConversationState(), "Conversation state should not be null");
        assertNotNull(conversation.getUserMessage(), "User message should not be null");
        */

    }
}
