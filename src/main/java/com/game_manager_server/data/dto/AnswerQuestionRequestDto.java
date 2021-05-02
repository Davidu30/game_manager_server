package com.game_manager_server.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerQuestionRequestDto {

    @NotEmpty(message = "UserId must not be empty")
    @Size(max = 50)
    @JsonProperty("userName")
    private String userName;

    @NotEmpty(message = "gameId must not be empty")
    @Size(max = 50)
    @JsonProperty("gameId")
    private String gameId;

    @NotEmpty(message = "answerId must not be empty")
    @Size(max = 50)
    @JsonProperty("answerId")
    private String answerId;

    @NotEmpty(message = "questionId must not be empty")
    @Size(max = 50)
    @JsonProperty("questionId")
    private String questionId;

}
