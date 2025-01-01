package org.casual_chess.cc_game.pubsub;

@FunctionalInterface
public interface IPubSubMessageHandler {
    void handle(String topic, String message);
}