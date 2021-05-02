package com.game_manager_server.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.game_manager_server.data.enums.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerQuestionResponseDto {

    @JsonProperty("answerStatus")
    private String answerStatus;

    @JsonProperty("pointsEarned")
    private int pointsEarned;

    public AnswerQuestionResponseDto(String answerStatus, int pointsEarned) {
        this.answerStatus = answerStatus + " answer";
        this.pointsEarned = pointsEarned;
    }
}
