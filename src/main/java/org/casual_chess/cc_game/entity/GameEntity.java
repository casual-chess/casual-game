package org.casual_chess.cc_game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Table("games")
public class GameEntity {

    @Id
    private UUID gameId;
    private Long playerWhite;
    private Long playerBlack;
    private GameStatus gameStatus;
}