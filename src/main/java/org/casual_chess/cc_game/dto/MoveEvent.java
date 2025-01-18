package org.casual_chess.cc_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MoveEvent {
    private String gameId;
    private String playerId;
    private String moveTime;
    private int moveNo;
    private String moveAlgebraic;
}
