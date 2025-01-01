package org.casual_chess.cc_game.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.casual_chess.cc_game.pubsub.IPubSubPublisher;
import org.casual_chess.cc_game.pubsub.IPubSubSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MoveHandlerService {
    private final IPubSubPublisher publisher;
    private final IPubSubSubscriber subscriber;

    @Autowired
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

        //* TODO: validate the move and update the game state
        //* TODO

        publisher.publish("game/{gameId}/state/updated", "UpdatedGameState");
    }
}
