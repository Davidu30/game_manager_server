package com.game_manager_server.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.game_manager_server.exceptions.GameManagerPersistencyException;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question {
    @JsonProperty("questionContent")
    private String questionContent;

    @JsonProperty("questionId")
    private final String questionId;

    @JsonProperty("answers")
    private List<Answer> answers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionId.equals(question.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId);
    }

    public Answer getAnswer(String answerId) throws GameManagerPersistencyException {
        for (Answer answer : answers){
            if(answer.getAnswerId().equalsIgnoreCase(answerId)){
                return answer;
            }
        }
        throw new GameManagerPersistencyException(String.format("AnswerId {} does not exist", answerId));
    }
}
