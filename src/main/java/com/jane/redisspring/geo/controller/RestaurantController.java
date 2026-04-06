package com.jane.redisspring.geo.controller;

import com.jane.redisspring.geo.dto.Restaurant;
import com.jane.redisspring.geo.service.RestaurantLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/geo")
public class RestaurantController {

    @Autowired
    private RestaurantLocatorService service;

    @GetMapping("/{zip}")
    public Flux<Restaurant> getRestaurants(@PathVariable String zip) {
        return this.service.getRestaurants(zip);
    }

}
