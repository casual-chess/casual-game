package org.casual_chess.cc_game.repository;

import org.casual_chess.cc_game.model.Game;

import java.util.UUID;

public interface IGameCacheRepository {
    void put(UUID gameId, Game game);
    void remove(UUID gameId);
    Game get(UUID gameId);
}
