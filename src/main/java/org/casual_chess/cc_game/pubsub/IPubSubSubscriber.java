package org.casual_chess.cc_game.pubsub;

public interface IPubSubSubscriber {
    void subscribe(String topic, IPubSubMessageHandler handler);
    void unsubscribe(String topic);
}
