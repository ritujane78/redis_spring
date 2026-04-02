package com.jane.redisspring.weather.service;

import reactor.core.publisher.Mono;

public interface WeatherService {
    Mono<Integer> getInfo(String zip);
}
