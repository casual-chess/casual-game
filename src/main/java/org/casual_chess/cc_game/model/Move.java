package org.casual_chess.cc_game.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class Move {
    private String moveNotation;
    private String player; // white or black

    //* copy constructor
    public Move(Move move) {
        this.moveNotation = move.moveNotation;
        this.player = move.player;
    }
}
