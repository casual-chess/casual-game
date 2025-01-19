package org.casual_chess.cc_game.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Move {
    private String moveNotation;
    private String player; // white or black
}
