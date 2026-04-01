package com.jane.redisspring.weather.service;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private ExternalServiceClient client;

    @Override
    @Cacheable("weather")
    public int getInfo(int zip) {
        return 0;
    }

    @Override
//    @Scheduled(fixedRate = 10_000)
    public void update() {
        System.out.println("updating weather");
        IntStream.rangeClosed(1,5)
                .forEach(this.client::getWeather);
    }
}
