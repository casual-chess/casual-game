package org.casual_chess.cc_game.repository.impl;

import org.casual_chess.cc_game.model.Game;
import org.casual_chess.cc_game.repository.IGameCacheRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryGameCacheRepository implements IGameCacheRepository {
    private final Map<String, Game> gameMap = new ConcurrentHashMap<>();

    @Override
    public void put(String gameId, Game game) {
        gameMap.put(gameId, game);
    }

    @Override
    public void remove(String gameId) {
        gameMap.remove(gameId);
    }

    @Override
    public Game get(String gameId) {
        return gameMap.get(gameId);
    }
}
