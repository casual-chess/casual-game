package org.casual_chess.cc_game.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@Table("moves")
public class MoveEntity {

    //*TODO: this should be part of primary key
//    @Id
    private UUID gameId; //* TODO: make it UUID

    @Id
    private int moveNumber;

    private String moveNotation;
    private PlayerColor player; //* TODO: make it enum
    private Timestamp moveAt;

    //* copy constructor
    public MoveEntity(MoveEntity move) {
        this.gameId = move.getGameId();
        this.moveNumber = move.getMoveNumber();
        this.moveNotation = move.getMoveNotation();
        this.player = move.getPlayer();
        this.moveAt = move.getMoveAt();
    }
}