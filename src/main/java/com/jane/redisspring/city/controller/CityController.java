package com.jane.redisspring.city.controller;

import com.jane.redisspring.city.dto.City;
import com.jane.redisspring.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/{zip}")
    public Mono<City> getCity(@PathVariable String zip) {
        return cityService.getCity(zip);

    }
}
