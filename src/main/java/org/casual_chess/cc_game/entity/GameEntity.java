package org.casual_chess.cc_game.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("games")
public class GameEntity {

    @Id
    private UUID gameId;
    private Long playerWhite;
    private Long playerBlack;
    private String gameStatus;
}