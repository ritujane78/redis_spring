package com.jane.redisspring.city.service;

import com.jane.redisspring.city.dto.City;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CityClient {

    private final WebClient webClient;

    public CityClient(@Value("${city.service.url}") String url){
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<City> getCity(String zip){
        return this.webClient
                .get()
                .uri("{zipCode}", zip)
                .retrieve()
                .bodyToMono(City.class);
    }

}
