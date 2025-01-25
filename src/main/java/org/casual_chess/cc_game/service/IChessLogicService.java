package org.casual_chess.cc_game.service;

import org.casual_chess.cc_game.entity.MoveEntity;
import org.casual_chess.cc_game.model.GameWithMoves;

public interface IChessLogicService {
    boolean isLegalMove(GameWithMoves game, MoveEntity move);
    GameWithMoves updateGameState(GameWithMoves game, MoveEntity move);
}
