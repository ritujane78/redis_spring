# Chat Service (Redis Pub/Sub with RTopicReactive)

## Overview
This module implements a real-time chat system using Redis Pub/Sub with Redisson's reactive API.

Messages are published to a Redis topic and broadcasted instantly to all subscribers.

---

## Redis Features Used

### 1. Pub/Sub Messaging (`RTopicReactive`)
- Redis Pub/Sub is used for real-time communication.
- Implemented using **Redisson's `RTopicReactive`**.
- Chat history saved using **Redisson's `RListReactive` **

### How it works:
- A topic acts as a channel.
- Messages published to the topic are **broadcasted to all subscribers**.

#### Example Flow:
Publisher → RTopicReactive → Redis → All Subscribers


---

### 2. Broadcasting Messages
- When a message is published:
  - It is pushed to all active listeners

---

### 3. Reactive Programming
- Non-blocking message handling
- Supports high concurrency and scalability

---

## Flow

1. User sends message
2. Backend publishes message using `RTopicReactive`
3. It is stored first in `RListReactive<String>` list
4. Redis broadcasts message to all subscribers
5. All connected clients receive message in real-time
6. All users who connect later can also view the chat history.

---

## Benefits

-  Ultra-low latency messaging
-  Real-time broadcast
- Reactive and scalable

---
