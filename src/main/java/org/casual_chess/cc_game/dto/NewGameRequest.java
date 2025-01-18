package org.casual_chess.cc_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class NewGameRequest {
    @JsonProperty("whitePlayerId")
    private String whitePlayerId;

    @JsonProperty("blackPlayerId")
    private String blackPlayerId;
}
