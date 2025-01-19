package org.casual_chess.cc_game.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Data
public class Game {
    UUID gameId;
    String whitePlayerId;
    String blackPlayerId;
    GameStatus gameStatus;
    List<Move> movesPlayed;

    //* copy constructor
    public Game(Game game) {
        this.gameId = game.gameId;
        this.whitePlayerId = game.whitePlayerId;
        this.blackPlayerId = game.blackPlayerId;
        this.gameStatus = game.gameStatus;
        this.movesPlayed = game.movesPlayed.stream().map(Move::new).collect(Collectors.toList());
    }

    public void addMove(Move move) {
        movesPlayed.add(move);
    }
}
