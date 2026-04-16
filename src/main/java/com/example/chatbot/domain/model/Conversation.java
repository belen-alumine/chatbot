package com.example.chatbot.domain.model;

import java.util.UUID;

public class Conversation {
    private final UUID conversationId;
    private ConversationState conversationState;
    private String userName;

    public Conversation() {
        this.conversationId = UUID.randomUUID();
        this.conversationState = ConversationState.NEW;
        System.out.println("Conversation was created: " + conversationId + ", with State:  " + conversationState);
    }

    public Conversation(UUID conversationId, ConversationState conversationState, String userName) {
        this.conversationId = conversationId;
        this.conversationState = conversationState;
        this.userName = userName;
    }

    public ConversationResponse handleUserMessage(String input) {
        System.out.println("Conversation received: " + conversationId + ", Input: " + input + "Conversation State this: " + this.conversationState);
        String cleanInput = input.toLowerCase().trim();
        ConversationState convState = this.conversationState;
        switch (convState) {
            case NEW:
                this.setConversationState(ConversationState.WAITING_FOR_NAME);
                System.out.println("Conversation received: " + this.conversationId +"Conversation State this: " + this.conversationState);
                return new ConversationResponse("¿Con quién estoy hablando?");
            case WAITING_FOR_NAME:
                conversationState = ConversationState.IN_PROGRESS;
                userName = input;
                return new ConversationResponse("¿En qué puedo ayudarte, " + input + "? \uD83D\uDE04");
            case WAITING_FOR_CHAT:
                conversationState = ConversationState.IN_PROGRESS;
                return new ConversationResponse("");
            case IN_PROGRESS: 
                String msg = "¿Algo más?";
                if (Patterns.esDespedida(cleanInput) || Patterns.isNothingElse(input)) {
                    msg = "Chau, " + userName + ", nos vemos pronto";
                    System.out.println("Conversation response: " + msg +"Conversation State this: " + conversationState);
                    conversationState = ConversationState.CLOSE;
                    return new ConversationResponse(msg);
                }
                String theme = Patterns.themeQuestion(cleanInput);
                if (!theme.equals("")) {
                    String response = Patterns.selectResponse(theme);
                    conversationState = ConversationState.IN_PROGRESS;
                    return new ConversationResponse(response + " ¿Algo más que quieras saber?");
                }
                conversationState = ConversationState.IN_PROGRESS;
                return new ConversationResponse("Perdón, no sé de eso \uD83E\uDD7A, ¿de qué otra cosa te gustaría hablar?");
            case CLOSE:
                System.out.println("Conversation State this: " + conversationState);
                return new ConversationResponse("La conversación se encuentra terminada \uD83D\uDE36\u200D\uD83C\uDF2B\uFE0F. Podés escribir 'Hola' para iniciar una nueva ");
            default:
                // String randomTheme = getRandomeTheme // buscar un tema random de la lista de temas que el bot conoce
                // devolver el tema random en el mensaje
                System.out.println("Conversation State this: " + conversationState);
                conversationState = ConversationState.IN_PROGRESS;
                return new ConversationResponse("Perdón, no sé de eso \uD83E\uDD7A, ¿de qué otra cosa te gustaría hablar?");
        }
    }

    private void setConversationState(ConversationState conversationState) {
        this.conversationState = conversationState;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public ConversationState getConversationState() {
        return conversationState;
    }

    public String getUserName() {
        return userName;
    }
}
