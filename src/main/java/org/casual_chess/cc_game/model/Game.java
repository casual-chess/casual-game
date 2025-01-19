package org.casual_chess.cc_game.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class Game {
    UUID gameId;
    String whitePlayerId;
    String blackPlayerId;
    GameStatus gameStatus;
    List<Move> movesPlayed;
}
