package com.example.chatbot.infrastructure.adapter.in.console;

import com.example.chatbot.application.port.in.HandleUserMessageInputPort;
import com.example.chatbot.domain.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInputAdapter implements CommandLineRunner {
    private HandleUserMessageInputPort useCase;

    public ConsoleInputAdapter(HandleUserMessageInputPort useCase) {
        this.useCase = useCase;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("*** Console started ***");
        start();
    }

    private void start() {
        User consoleUser = useCase.createUser();
        Scanner scanner = new Scanner(System.in);

        System.out.print("¡Hola!, soy el ChatBot de pruebas Java \uD83E\uDD16 ");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            String response = useCase.handleUserMessage(consoleUser.getUserId(), input);

            System.out.println(response);
        }
    }
}