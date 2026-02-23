package com.example.chatbot;

import com.example.chatbot.infrastructure.adapter.in.console.ConsoleInputAdapter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        }
)

public class ChatbotApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatbotApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ConsoleInputAdapter console) {
        return args -> console.start();
    }

    /*
	public static void main(String[] args) {

        SpringApplication.run(ChatbotApplication.class, args);

        ChatBot chatBot = new ChatBot("Botcito");
        User consoleUser = chatBot.createUser();

        HandleUserMessageInputPort useCase = new HandleUserMessageUseCase(chatBot);

        ConsoleInputAdapter console =
                new ConsoleInputAdapter(useCase);

        console.start(consoleUser);


	}*/
}
