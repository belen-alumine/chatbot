package com.example.chatbot.domain.service;

public record Weather(
        double temp
) {
    public String getWeatherDescribe() {
        if (temp < 15) return "frio";
        if (temp > 28) return "calor";
        return "templado";
    }
}