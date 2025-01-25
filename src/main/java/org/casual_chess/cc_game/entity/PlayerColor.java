package org.casual_chess.cc_game.entity;

import lombok.Getter;

@Getter
public enum PlayerColor {
    white("white"),
    black("black");

    private final String color;

    PlayerColor(String color) {
        this.color = color;
    }
}
