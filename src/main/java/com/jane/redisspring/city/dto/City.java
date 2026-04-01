package com.jane.redisspring.city.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String zip;
    private String city;
    private String stateName;
    private int temperature;
}
