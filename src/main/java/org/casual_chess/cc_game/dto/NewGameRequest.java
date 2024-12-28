package org.casual_chess.cc_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class NewGameRequest {
    @JsonProperty("white_player_id")
    private String whitePlayerId;

    @JsonProperty("black_player_id")
    private String blackPlayerId;
}
