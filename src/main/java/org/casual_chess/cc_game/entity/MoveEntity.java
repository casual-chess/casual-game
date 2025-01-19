package org.casual_chess.cc_game.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("moves")
public class MoveEntity {

    //*TODO: this should be part of primary key
//    @Id
    private String gameId;

    @Id
    private int moveNumber;

    private String moveNotation;
    private String player;
}