package com.example.chatbot.infrastructure.adapter.in;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HelloMessages {

    public HelloMessages() {
        System.out.println("Hello World!");
    }

    public String selectRandomMessage() {
        System.out.println("Selecting Random Message");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Messages.json"));
            String message = reader.lines().toString();

            System.out.println(message);
            reader.close();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
