package com.jane.redisspring.city.service;

import com.jane.redisspring.city.dto.City;
import org.redisson.RedissonReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityClient cityClient;

    private RMapReactive<String, City> cityRMap;

    public CityServiceImpl(RedissonReactiveClient redissonClient) {
        this.cityRMap = redissonClient.getMap("city", new TypedJsonJacksonCodec(City.class));
    }
    @Override
    public Mono<City> getCity(String zip) {
        return this.cityRMap.get(zip)
                .switchIfEmpty(this.cityClient.getCity(zip)
                        .flatMap(c -> this.cityRMap.fastPut(zip, c).thenReturn(c))
                );
    }
}
