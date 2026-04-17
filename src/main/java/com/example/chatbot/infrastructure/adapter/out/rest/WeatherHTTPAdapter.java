package com.example.chatbot.infrastructure.adapter.out.rest;

import com.example.chatbot.domain.WeatherExterno;
import com.example.chatbot.domain.service.Weather;
import com.example.chatbot.infrastructure.adapter.out.rest.dtos.WeatherExternalDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Optional;

@Component
public class WeatherHTTPAdapter implements WeatherExterno {
    private final RestTemplate restTemplate;
    private final String apiUrl = "https://api.open-meteo.com/v1/forecast";

    public WeatherHTTPAdapter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<Weather> getWeather(double lat, double lon) {
        try {

            String url = String.format(
                    Locale.US,
                    apiUrl + "?latitude=%f&longitude=%f&current=temperature_2m",
                    lat, lon
            );
            //System.out.println(url);

            WeatherExternalDTO response = restTemplate.getForObject(url, WeatherExternalDTO.class);
            return Optional.of(new Weather(response.current().temperature_2m()));
        } catch (Exception e) {
            System.err.println("Error al obtener clima: " + e.getMessage());
            return Optional.empty();
        }

    }
}
