package org.casual_chess.cc_game.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.casual_chess.cc_game.entity.GameEntity;
import org.casual_chess.cc_game.entity.GameStatus;
import org.casual_chess.cc_game.entity.MoveEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@SuperBuilder
@Data
public class GameWithMoves extends GameEntity {
    List<MoveEntity> movesPlayed;


    public GameWithMoves(GameEntity gameEntity) {
        this.setGameId(gameEntity.getGameId());
        this.setPlayerWhite(gameEntity.getPlayerWhite());
        this.setPlayerBlack(gameEntity.getPlayerBlack());
        this.setGameStatus(gameEntity.getGameStatus());
        movesPlayed = new ArrayList<>();
    }

    public GameWithMoves(GameEntity gameEntity, List<MoveEntity> moves) {
        this(gameEntity);
        addMoves(moves);
    }

    //* copy constructor
    public GameWithMoves(GameWithMoves game) {
        this((GameEntity) game);
        this.movesPlayed = game.movesPlayed.stream().map(MoveEntity::new).collect(Collectors.toList());
    }

    public void addMove(MoveEntity move) {
        movesPlayed.add(move);
    }

    public void addMoves(List<MoveEntity> moves) {
        movesPlayed.addAll(moves);
    }

    /**
     * @return The player to move: WHITE_TO_MOVE or BLACK_TO_MOVE, if game is ONGOING. Otherwise, null.
     */
    public GameStatus getPlayerToMove() {
        if (!getGameStatus().equals(GameStatus.ongoing)) {
            return null;
        } else {
            return movesPlayed.size() % 2 == 0 ? GameStatus.white_to_move : GameStatus.black_to_move;
        }
    }

}
