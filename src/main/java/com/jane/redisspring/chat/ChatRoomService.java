package com.jane.redisspring.chat;

import org.redisson.api.RListReactive;
import org.redisson.api.RTopicReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
public class ChatRoomService implements WebSocketHandler {

    @Autowired
    private RedissonReactiveClient client;

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String roomName = getChatRoomName(session);
        RTopicReactive rTopicReactive = client.getTopic(roomName, StringCodec.INSTANCE);
        RListReactive<String> list = client.getList("history:" + roomName, StringCodec.INSTANCE);;

        session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(msg -> list.add(msg).then(rTopicReactive.publish(msg)))
                .doOnError(System.err::println)
                .doFinally(s -> System.out.println("Subscriber finally " + s))
                .subscribe();

        Flux<WebSocketMessage> flux = rTopicReactive.getMessages(String.class)
                .startWith(list.iterator())
                .map(session::textMessage)
                                .doOnError(System.out::println)
                .doFinally(s -> System.out.println("publisher finally " + s));

        return session.send(flux);

    }

    private String getChatRoomName(WebSocketSession session) {
        URI uri = session.getHandshakeInfo().getUri();
        return UriComponentsBuilder.fromUri(uri)
                .build()
                .getQueryParams()
                .toSingleValueMap()
                .getOrDefault("room", "default");
    }
}
