package com.example.chatbot.domain;

import com.example.chatbot.domain.service.Weather;

import java.util.Optional;

public interface WeatherExterno {
    Optional<Weather> getWeather(double lat, double lon);
}
