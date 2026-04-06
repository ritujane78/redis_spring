package com.jane.redisspring.geo.service;

import com.jane.redisspring.geo.dto.GeoLocation;
import com.jane.redisspring.geo.dto.Restaurant;
import org.redisson.api.GeoUnit;
import org.redisson.api.RGeoReactive;
import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.geo.GeoSearchArgs;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
public class RestaurantLocatorService {
    private RGeoReactive<Restaurant> geo;
    private RMapReactive<String, GeoLocation> map;

    private RedissonReactiveClient client;

    public RestaurantLocatorService(RedissonReactiveClient client) {
        this.client = client;
        this.geo = this.client.getGeo("restaurants", new TypedJsonJacksonCodec(Restaurant.class));;
        this.map = this.client.getMap("usa", new TypedJsonJacksonCodec(String.class, GeoLocation.class));;
    }

    public Flux<Restaurant> getRestaurants(final String zipCode) {
        return this.map.get(zipCode)
                .doOnNext(gl -> System.out.println("GeoLocation: " + gl))
                .map(gl -> GeoSearchArgs.from(gl.getLongitude(), gl.getLatitude()).radius(5, GeoUnit.MILES))
                .flatMap(gs -> this.geo.search(gs))
                .flatMapIterable(Function.identity());
    }
}
