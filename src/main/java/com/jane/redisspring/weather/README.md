# Weather Service (Caching with RMapCache)

## Overview
This module fetches weather data from an external API and caches it using Redis.

---

## Redis Features Used

### 1. Cache Storage (`RMapCache`)
- Uses **Redisson's `RMapCache`**
- Stores weather data as key-value pairs

#### Structure:
```
zipcode → Weather Data
```

---

### 2. TTL (Time-To-Live)
- Weather data is cached temporarily
- Entries expire after a defined duration

---

### 3. Reduced API Calls
- Prevents repeated calls to external weather APIs
- Improves performance and reduces cost

---

## Flow

1. Request weather by zipcode
2. Check Redis (`RMapCache`)
3. If not found:
    - Fetch from API
    - Store in Redis with TTL
4. Return response

---

## Benefits

-  Faster responses
-  Reduced API usage
-  Automatic cache expiry

---
