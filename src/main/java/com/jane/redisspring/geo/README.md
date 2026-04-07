# Geo Service (Redis Geospatial Indexing + Reactive Mapping)

## Overview
This module demonstrates location-based search using Redis GEO features with Spring Boot and Redisson.

It allows users to input a **zipcode**, and then displays all nearby restaurants within a **5-mile radius** on an OpenStreetMap-based frontend.

---

## Redis Features Used

### 1. Geospatial Indexing (GEO)
- Redis provides built-in support for geospatial data.
- Stores coordinates (latitude & longitude) and enables:
    - Radius search
    - Distance calculations
    - Nearby queries

Internally, Redis uses sorted sets to efficiently perform geo queries.

---

### 2. Radius Queries
- Core feature used in this module.
- Finds restaurants within a **5-mile radius** of a given location.
- Results are sorted based on distance.

---

### 3. Redisson Reactive API (`RGeoReactive`)
- Used for non-blocking, reactive geospatial operations.
- Enables scalable and efficient querying of nearby locations.

---

### 4. Reactive Key-Value Mapping (`RMapReactive`)
- A Redis map is used to store zipcode-to-location mapping.
