package com.jane.redisspring.weather.service;

import com.jane.redisspring.city.dto.City;
import com.jane.redisspring.city.service.CityClient;
import com.jane.redisspring.weather.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;


@Service
public class ExternalServiceClient {
    private WebClient webClient;

    public ExternalServiceClient(@Value("${city.service.url}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<Integer> getWeather(String zip) {
        return this.webClient
                .get()
                .uri("{zipCode}", zip)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .map(WeatherResponse::getTemperature);
    }
}
