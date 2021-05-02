package com.game_manager_server.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Game {

    @JsonProperty("gameId")
    private final String gameId;

    @JsonProperty("players")
    private HashMap<String, Player> players = new HashMap<>();

    @JsonProperty("questions")
    private HashMap<String, Question> questions = new HashMap<>();

}


