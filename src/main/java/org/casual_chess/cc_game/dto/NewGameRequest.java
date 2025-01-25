package org.casual_chess.cc_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class NewGameRequest {
    @JsonProperty("whitePlayerId")
    private Long whitePlayerId;

    @JsonProperty("blackPlayerId")
    private Long blackPlayerId;
}
