package com.game_manager_server.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.game_manager_server.data.enums.AnswerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer {
    private String answerContent;

    private String answerId;

    private AnswerStatus answerStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return answerId.equals(answer.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId);
    }
}
