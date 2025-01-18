package org.casual_chess.cc_game.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Game {
    String gameId;
    String whitePlayerId;
    String blackPlayerId;
    GameStatus gameStatus;
    String fen;
    //list of moves played 
}
