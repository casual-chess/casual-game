package org.casual_chess.cc_game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.casual_chess.cc_game.model.Game;

@Data
@AllArgsConstructor
public class InvalidMoveEvent {
    MoveEvent moveEvent;
    Game gameState;
}
