# City Service (Reactive Cache with RMapCacheReactive)

## Overview
This module manages city data using Redis as a reactive cache layer.

---

## Redis Features Used

### 1. Reactive Cache (`RMapCacheReactive`)
- Uses **Redisson's `RMapCacheReactive`**
- Stores key-value pairs with optional TTL

#### Structure:
```
cityId → City Object
```

---

### 2. TTL Support
- Entries can expire automatically
- Helps keep cache fresh

---

### 3. Reactive Data Access
- Non-blocking operations
- Suitable for high-performance applications

---

## Flow

1. Request city data
2. Check Redis (`RMapCacheReactive`)
    - If present → return cached data
    - If not → fetch and cache
3. Return response

---

## Benefits

-  Fast reads
-  Reactive and scalable
-  Built-in expiration support

---