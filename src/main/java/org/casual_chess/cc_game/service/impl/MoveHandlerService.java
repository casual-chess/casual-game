package org.casual_chess.cc_game.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.casual_chess.cc_game.pubsub.IPubSubPublisher;
import org.casual_chess.cc_game.pubsub.IPubSubSubscriber;
import org.casual_chess.cc_game.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class MoveHandlerService {
    private final IPubSubPublisher publisher;
    private final IPubSubSubscriber subscriber;

    @Autowired
    IUserRepository userRepository;

    public MoveHandlerService(IPubSubPublisher publisher, IPubSubSubscriber subscriber) {
        this.publisher = publisher;
        this.subscriber = subscriber;
    }

    @PostConstruct
    public void subscribeToMoveEvents() {
        subscriber.subscribe("game/*/move/made", this::handleUserMoveEvent);
    }

    public void handleUserMoveEvent(String topic, String message) {
        log.info("Received topic: {}, move event: {}", topic, message);
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Current Thread: " + Thread.currentThread());
        System.out.flush();

        Mono.delay(Duration.ofSeconds(20))
                .then(userRepository.findByUsername("player1"))
                .subscribe(userEntity -> {
                    System.out.println("User: " + userEntity + ", currentThread: " + Thread.currentThread());
                    System.out.flush();
                });


        //* TODO: validate the move and update the game state
        //* TODO

        publisher.publish("game/{gameId}/state/updated", "UpdatedGameState");
    }
}
