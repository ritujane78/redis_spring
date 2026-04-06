package com.jane.redisspring.geo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jane.redisspring.geo.dto.Restaurant;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RestaurantUtil {

    public static List<Restaurant> getRestaurants() {
            ObjectMapper mapper = new ObjectMapper();

            InputStream stream =
                    RestaurantUtil.class.getClassLoader().getResourceAsStream("restaurant.json");

            if (stream == null) {
                throw new RuntimeException("restaurant.json NOT found in classpath (check src/test/resources)");
            }

            try {
                return mapper.readValue(stream, new TypeReference<List<Restaurant>>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }