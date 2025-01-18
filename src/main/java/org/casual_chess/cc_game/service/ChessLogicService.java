package org.casual_chess.cc_game.service;

import org.casual_chess.cc_game.model.Game;
import org.casual_chess.cc_game.model.Move;

public interface ChessLogicService {
    boolean isLegalMove(Game game, Move move);
    Game updateGameState(Game game, Move move);
}
