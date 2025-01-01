package org.casual_chess.cc_game.pubsub.redis;

import org.casual_chess.cc_game.pubsub.IPubSubPublisher;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisPublisherImpl implements IPubSubPublisher {
    private final StringRedisTemplate redisTemplate;

    public RedisPublisherImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(String topic, String message) {
        redisTemplate.convertAndSend(topic, message);
    }
}
