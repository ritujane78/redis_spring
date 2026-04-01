package com.jane.redisspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisSpringApplication.class, args);
	}

}
