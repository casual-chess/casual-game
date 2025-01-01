package org.casual_chess.cc_game.pubsub.redis;

import org.casual_chess.cc_game.pubsub.IPubSubMessageHandler;
import org.casual_chess.cc_game.pubsub.IPubSubSubscriber;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.connection.MessageListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RedisSubscriberImpl implements IPubSubSubscriber {
    private final RedisMessageListenerContainer container;
    private final Map<String, MessageListener> topicListeners;

    public RedisSubscriberImpl(RedisConnectionFactory connectionFactory) {
        this.container = new RedisMessageListenerContainer();
        this.container.setConnectionFactory(connectionFactory);
        this.container.afterPropertiesSet(); // Ensure proper initialization
        this.container.start();

        topicListeners = new ConcurrentHashMap<>();
    }

    @Override
    public void subscribe(String topic, IPubSubMessageHandler handler) {
        container.addMessageListener(
                (Message message, byte[] pattern) -> handler.handle(new String(pattern), new String(message.getBody())),
                new PatternTopic(topic)
        );
    }

    @Override
    public void unsubscribe(String topicPattern) {
        MessageListener listener = topicListeners.get(topicPattern);
        if (listener == null) {
            return;
        }
        topicListeners.remove(topicPattern);
        container.removeMessageListener(listener);
    }

}
