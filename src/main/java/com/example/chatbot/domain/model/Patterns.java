package com.example.chatbot.domain.model;

import com.example.chatbot.domain.WeatherExterno;
import com.example.chatbot.domain.service.Weather;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class Patterns {
    private static WeatherExterno weatherExterno;

    public Patterns(WeatherExterno weatherExterno) {
        this.weatherExterno = weatherExterno;
    }


    private static final List<String> SALUDOS = Arrays.asList("hola", "buen día", "qué tal", "buenas");
    private static final List<String> DESPEDIDAS = Arrays.asList("chau", "nos vemos", "hasta la proxima", "chaucito", "chauchis", "adios", "hasta luego");
    private static final List<String> HOUR = Arrays.asList("qué hora es?", "la hora", "saber la hora", "quiero saber la hora", "me decis la hora?", "me podes decir la hora?");
    private static final List<String> NOTHING_ELSE = Arrays.asList("no, nada mas", "nada mas", "eso es todo, gracias", "eso es todo", "no gracias");
    private static final List<String> WEATHER = Arrays.asList("el clima", "clima", "como esta el clima", "que temperatura hace", "me podes decir el clima", "me podes decir la temperatura");

    public boolean esSaludo(String input) {
        return SALUDOS.contains(input.toLowerCase().trim());
    }

    public static boolean esDespedida(String input) {
        return DESPEDIDAS.contains(input.toLowerCase().trim());
    }

    public static boolean isNothingElse(String input) {
        return NOTHING_ELSE.contains(input.toLowerCase().trim());
    }

    public static String themeQuestion(String input) {
        String theme = "";
        if (HOUR.contains(input.toLowerCase().trim())) {
            theme = "HOUR";
        } else if (WEATHER.contains(input.toLowerCase().trim())) {
            theme = "WEATHER";
        }
        return theme;
    }

    public static String selectResponse(String input) {
        String response = "";
        if (input.equals("HOUR")) {
            LocalDateTime ahora = LocalDateTime.now();

            response = "Es la " + ahora + " ⌚"; // TODO: poner condición para que diga 'es la/son las' según corresponda
        } else if (input.equals("WEATHER")) {
            Optional<Weather> weather = weatherExterno.getWeather(-34.6037, -58.3816); // TODO: reemplazar por coordenadas del usuario
            if (weather.isPresent()) {
                response = "El clima actual es de " + weather.get().temp() + "°C 🌡️";
            } else {
                response = "Lo siento, no pude obtener el clima en este momento.";
            }
        }
        return response;
    }

}
