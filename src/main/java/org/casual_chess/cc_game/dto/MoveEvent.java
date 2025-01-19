package org.casual_chess.cc_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class MoveEvent {
    private UUID gameId;
    private int moveNo;
    private Long playerId;
    private String playerColor;
    private String moveAlgebraic;
//    private String moveTime;
}
