package com.jane.redisspring.fib.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class FibServiceImpl implements FibService {

    @Override
    @Cacheable(value = "maths:fib", key = "#index")
    public int getFib(int index, String name) {
        System.out.println("getFib(" + index + ", for " + name + ")");
        if(index <2) return index;
        return getFib(index-1, name) + getFib(index-2, name);
    }
}
