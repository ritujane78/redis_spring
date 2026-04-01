package com.jane.redisspring.weather.service;

public interface WeatherService {
    int getInfo(int zip);
    void update();
}
