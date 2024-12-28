package org.casual_chess.cc_game.service;

import org.casual_chess.cc_game.model.Game;

public interface GameCacheRepository {
    void put(String gameId, Game game);
    void remove(String gameId);
    Game get(String gameId);
}
