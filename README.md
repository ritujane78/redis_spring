# Redis - Spring Boot (Reactive + Redisson)

This project demonstrates various methods of caching using Redis in a Java Spring Boot application, with a focus on **Reactive programming (Project Reactor)** and **Redisson clients**.

---

## Tech Stack

- Java 21+
- Spring Boot
- Spring WebFlux (Reactive)
- Project Reactor (Mono / Flux)
- Redis
- Redisson (Reactive Client)

---

##  Features

- Reactive Redis integration
- Annotation-based caching
- Programmatic caching using Redisson
- Scheduled cache eviction / refresh
- High-performance non-blocking APIs

---

## Caching Annotations

Spring provides powerful caching abstractions via annotations:

### 1. `@Cacheable`
- Used to cache method results.
- If data exists in cache → returned directly.
- Otherwise → method executes and result is cached.

```java
@Cacheable(value = "users", key = "#id")
public Mono<User> getUser(int id) {
    return repository.findById(id);
}
```

---

### 2. `@CachePut`
- Always executes the method and updates the cache.

```java
@CachePut(value = "users", key = "#user.id")
public Mono<User> updateUser(User user) {
    return repository.save(user);
}
```

---

### 3. `@CacheEvict`
- Removes entries from cache.

```java
@CacheEvict(value = "users", key = "#id")
public Mono<Void> deleteUser(int id) {
    return repository.deleteById(id);
}
```

#### Additional options:
- `allEntries = true` → clears entire cache
- `beforeInvocation = true` → evict before method execution

---

### 4. `@Scheduled`
- Used for periodic cache refresh or eviction.

```java
@Scheduled(fixedRate = 60000)
@CacheEvict(value = "users", allEntries = true)
public void clearCache() {
    System.out.println("Cache cleared!");
}
```

---

### 5. `@EnableCaching`
- Enables Spring caching support.

```java
@SpringBootApplication
@EnableCaching
public class Application {}
```

---

## Redisson Clients Overview

Redisson provides distributed and reactive Redis-based data structures.

### 🔹 RedissonClient (Synchronous)
- Traditional blocking API.

### 🔹 RedissonReactiveClient
- Non-blocking reactive API (recommended for WebFlux).

```java
@Bean
public RedissonReactiveClient redissonReactiveClient() {
    Config config = new Config();
    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    return Redisson.createReactive(config);
}
```

---

## Important Redisson Reactive Data Structures

### 1. `RMapCacheReactive<K, V>`
- A distributed map with TTL support.

```java
RMapCacheReactive<String, String> map = redisson.getMapCache("cache");

map.put("key", "value", 10, TimeUnit.MINUTES);
map.get("key").subscribe(System.out::println);
```

**Use cases:**
- Cache with expiration
- Session storage
- Rate limiting

---

### 2. `RMapReactive<K, V>`
- Standard distributed map without TTL.

```java
RMapReactive<String, String> map = redisson.getMap("map");
```

---

### 3. `RBucketReactive<V>`
- Simple key-value storage.

```java
RBucketReactive<String> bucket = redisson.getBucket("key");
bucket.set("value");
```

---

### 4. `RListReactive<V>`
- Distributed list.

```java
RListReactive<String> list = redisson.getList("list");
list.add("item");
```

---

### 5. `RSetReactive<V>`
- Distributed set.

```java
RSetReactive<String> set = redisson.getSet("set");
```

---

### 6. `RLockReactive`
- Distributed lock.

```java
RLockReactive lock = redisson.getLock("lock");
lock.lock().subscribe();
```

---

## Reactive Programming Tips

- Always return `Mono` or `Flux` in WebFlux applications.
- Avoid blocking calls (`.block()`) in production.
- Chain operations using `.flatMap()` instead of nested calls.

---

## Best Practices

- Use `RMapCacheReactive` for caching with TTL.
- Combine Spring Cache annotations with Redisson for flexibility.
- Use `@Scheduled` for periodic cache invalidation.
- Avoid large objects in Redis.
- Use proper serialization (JSON / Kryo / Jackson).

---
