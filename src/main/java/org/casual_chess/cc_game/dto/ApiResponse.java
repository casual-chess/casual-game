package org.casual_chess.cc_game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("data")
    private T data;
}
