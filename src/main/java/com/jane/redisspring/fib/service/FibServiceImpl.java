package com.jane.redisspring.fib.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FibServiceImpl implements FibService {

    @Override
    @Cacheable(value = "maths:fib", key = "#index")
    public int getFib(int index) {
        System.out.println("getFib(" + index + ")");
        if(index <2) return index;
        return getFib(index-1) + getFib(index-2);
    }

    @Override
    @CacheEvict(key = "#index", value = "maths:fib")
    public void clearCache(int index) {
        System.out.println("clearCache(" + index + ")");

    }
}
