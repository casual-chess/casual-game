package org.casual_chess.cc_game.repository;

import org.casual_chess.cc_game.model.Game;

public interface IGameCacheRepository {
    void put(String gameId, Game game);
    void remove(String gameId);
    Game get(String gameId);
}
