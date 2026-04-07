# Fibonacci Service (Spring Cache + Redis)

## Overview
This module computes Fibonacci numbers and uses Redis-backed caching to optimize repeated calculations.

---

## Redis Features Used

### 1. Spring Cache Abstraction
- Uses annotations:
    - `@Cacheable`
    - `@CacheEvict`

---

### 2. @Cacheable
- Automatically stores method results in Redis
- If the same input is requested again:
    - Value is returned from cache
    - No recomputation needed

#### Example:
```
@Cacheable(value = "maths:fib", key = "#index")
```

---

### 3. @CacheEvict
- Used to remove entries from cache
- Helps maintain consistency when needed

#### Example:
```
@CacheEvict(key = "#index", value = "maths:fib")
```

---

### 4. Redis as Backend Cache
- Spring Cache is backed by Redis
- Stores computed Fibonacci values as key-value pairs

---

## Flow

1. Request fib(n)
2. Check cache via `@Cacheable`
3. If not present:
    - Compute value
    - Store in Redis
4. Return result

---

## Benefits

- Avoids recomputation
- Improves performance
- Transparent caching (via annotations)

---
