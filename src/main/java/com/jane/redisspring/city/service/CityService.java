package com.jane.redisspring.city.service;

import com.jane.redisspring.city.dto.City;
import reactor.core.publisher.Mono;

public interface CityService {
    Mono<City> getCity(final String zip);
}
