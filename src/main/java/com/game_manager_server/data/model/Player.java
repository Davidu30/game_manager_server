package com.game_manager_server.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {

    @JsonProperty("userName")
    private final String userName;

    @JsonProperty("earnedPoints")
    private int earnedPoints;

    @JsonProperty("answeredQuestionIds")
    private Set<String> answeredQuestionIds = new HashSet<>();

    public void addEaredPoints(int numPoint) {
        earnedPoints += numPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return userName.equals(player.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
