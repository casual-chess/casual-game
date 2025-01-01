package org.casual_chess.cc_game.pubsub;

public interface IPubSubPublisher {
    void publish(String topic, String message);
}
