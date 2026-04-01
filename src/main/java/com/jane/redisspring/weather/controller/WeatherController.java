package com.jane.redisspring.weather.controller;

import com.jane.redisspring.weather.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {
    private WeatherService weatherService;

    @GetMapping("/{zip}")
    public Mono<Integer> getWeatherByZip(@PathVariable int zip) {
        return Mono.fromSupplier(() -> weatherService.getInfo(zip));
    }
}
