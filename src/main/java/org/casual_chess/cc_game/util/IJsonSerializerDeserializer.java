package org.casual_chess.cc_game.util;

public interface IJsonSerializerDeserializer {
    String serialize(Object object);
    <T> T deserialize(String json, Class<T> clazz);
}
