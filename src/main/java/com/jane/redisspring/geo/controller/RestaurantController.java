package com.jane.redisspring.geo.controller;

import com.jane.redisspring.geo.dto.Restaurant;
import com.jane.redisspring.geo.service.RestaurantLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "http://localhost:63342")
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
