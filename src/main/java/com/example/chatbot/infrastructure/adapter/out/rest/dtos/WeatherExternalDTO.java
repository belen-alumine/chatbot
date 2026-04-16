package com.example.chatbot.infrastructure.adapter.out.rest.dtos;

public record WeatherExternalDTO(
        double latitude,
        double longitude,
        CurrentWeather current
) {
    public record CurrentWeather(
            String time,
            double temperature_2m
    ) {}
}