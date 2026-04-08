package com.example.chatbot.domain.model;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Patterns {
    private static final List<String> SALUDOS = Arrays.asList("hola", "buen día", "qué tal", "buenas");
    private static final List<String> DESPEDIDAS = Arrays.asList("chau", "nos vemos", "hasta la proxima", "chaucito", "chauchis", "adios", "hasta luego");

    public boolean esSaludo(String input) {
        return SALUDOS.contains(input.toLowerCase().trim());
    }

    public static boolean esDespedida(String input) {
        return DESPEDIDAS.contains(input.toLowerCase().trim());
    }

    //PROBAR ESTO:
    //private static final List<String> SALUDOS = List.of("hola", "buen día", "qué tal", "buenas");

    //public boolean esSaludo(String input) {
    //    if (input == null) return false;
    //    return SALUDOS.stream().anyMatch(input.toLowerCase()::contains);
    //}
}
