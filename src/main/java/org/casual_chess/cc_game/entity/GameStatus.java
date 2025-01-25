package org.casual_chess.cc_game.entity;

import lombok.Getter;

@Getter
public enum GameStatus {
    ongoing("ongoing"),
    draw("draw"),
    white("white"), // white won
    black("black"), // black won

    //* ongoing can be either white or black to move
    white_to_move("white_to_move"),
    black_to_move("black_to_move");


    private final String status;

    GameStatus(String status) {
        this.status = status;
    }
}