package com.jane.redisspring.weather.service;

import lombok.AllArgsConstructor;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private ExternalServiceClient client;


    RMapCacheReactive<String, Integer> weatherCache;

    public WeatherServiceImpl(RedissonReactiveClient redissonReactiveClient) {
        this.weatherCache = redissonReactiveClient.getMapCache("weather", new TypedJsonJacksonCodec(Integer.class));
    }

    @Override
    public Mono<Integer> getInfo(String zip) {
        return this.weatherCache.get(zip)
                .switchIfEmpty(this.client.getWeather(zip)
                        .flatMap(i -> this.weatherCache.fastPut(zip, i, 10, TimeUnit.SECONDS).thenReturn(i))
                );
    }
}
