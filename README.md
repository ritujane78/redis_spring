# Redis - Spring Boot (Reactive + Redisson)

This project demonstrates various methods of caching and real-time data handling using Redis in a Java Spring Boot application, with a focus on **Reactive programming (Project Reactor)** and **Redisson clients**.

---

## Tech Stack

- Java 21+
- Spring Boot
- Spring WebFlux (Reactive)
- Project Reactor (Mono / Flux)
- Redis
- Redisson (Reactive Client)

---

## Features

- Reactive Redis integration
- Annotation-based caching
- Programmatic caching using Redisson
- Scheduled cache eviction / refresh
- High-performance non-blocking APIs
- Real-time messaging using Redis Pub/Sub

---

## Redis Use Cases in This Project

| Module   | Redis Feature Used |
|----------|------------------|
| chat     | Pub/Sub (`RTopicReactive`) |
| city     | Reactive Cache (`RMapCacheReactive`) |
| weather  | Cache with TTL (`RMapCache`) |
| geo      | Geospatial (`RGeoReactive`) + Mapping (`RMapReactive`) |
| fib      | Spring Cache (`@Cacheable`, `@CacheEvict`) |

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
