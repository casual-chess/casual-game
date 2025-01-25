package org.casual_chess.cc_game.repository;

import org.casual_chess.cc_game.model.GameWithMoves;

import java.util.UUID;

public interface IGameCacheRepository {
    void put(UUID gameId, GameWithMoves game);
    void remove(UUID gameId);
    GameWithMoves get(UUID gameId);
}
