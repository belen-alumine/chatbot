package com.example.chatbot.infrastructure.adapter.in.console;

import com.example.chatbot.application.port.in.HandleUserMessageInputPort;
import com.example.chatbot.domain.model.ChatBot;
import com.example.chatbot.domain.model.User;
import org.springframework.stereotype.Component;


import java.util.Scanner;

@Component
public class ConsoleInputAdapter {

    private final HandleUserMessageInputPort inputPort;
    private final Scanner scanner;
    private final ChatBot chatBot;

    public ConsoleInputAdapter(HandleUserMessageInputPort inputPort, ChatBot chatBot) {
        this.inputPort = inputPort;
        this.scanner = new Scanner(System.in);
        this.chatBot = chatBot;
    }

    public void start() {

        System.out.println("Chat started. Type something:");
        User consoleUser = chatBot.createUser();

        while (true) {

            String input = scanner.nextLine();

            String response = inputPort.handleUserMessage(
                    consoleUser.getUserId(),
                    input
            );

            System.out.println(response);
        }
    }
}