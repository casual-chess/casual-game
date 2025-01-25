package org.casual_chess.cc_game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.casual_chess.cc_game.model.GameWithMoves;

@Data
@AllArgsConstructor
public class InvalidMoveEvent {
    MoveEvent moveEvent;
    GameWithMoves gameState;
}
