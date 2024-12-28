package org.casual_chess.cc_game.service.impl;

import org.casual_chess.cc_game.model.Game;
import org.casual_chess.cc_game.service.GameCacheRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InMemoryGameCacheRepository implements GameCacheRepository {
    private final Map<String, Game> gameMap = new HashMap<>();

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
