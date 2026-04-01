package com.jane.redisspring.city.service;

import com.jane.redisspring.city.dto.City;
import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityClient cityClient;

    private RMapCacheReactive<String, City> cityRMap;

    public CityServiceImpl(RedissonReactiveClient redissonClient) {
        this.cityRMap = redissonClient.getMapCache("city", new TypedJsonJacksonCodec(City.class));
    }
    @Override
    public Mono<City> getCity(String zip) {
        return this.cityRMap.get(zip)
                .switchIfEmpty(this.cityClient.getCity(zip)
                        .flatMap(c -> this.cityRMap.fastPut(zip, c, 10, TimeUnit.SECONDS        ).thenReturn(c))
                );
    }
}
