package com.game_manager_server.factory;

import com.game_manager_server.data.enums.AnswerStatus;
import com.game_manager_server.data.model.Answer;
import com.game_manager_server.data.model.Question;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class QuestionsFactory {



    public static HashMap<String,Question> generatesQuestions(int numOfQuestion){
        HashMap<String,Question> questionList = new HashMap<>();
        int questionId = 0;
        for(int i = 0; i < numOfQuestion; i++){
            String questionIdS = String.valueOf(questionId++);
            Question question = new Question(questionIdS);
            question.setAnswers(generateAnswer());
            questionList.put(questionIdS, question);
        }
        return questionList;
    }

    private static List<Answer> generateAnswer() {
        return Arrays.asList(
                Answer.builder().answerId("0").answerStatus(AnswerStatus.RIGHT).build(),
                Answer.builder().answerId("1").answerStatus(AnswerStatus.WRONG).build(),
                Answer.builder().answerId("2").answerStatus(AnswerStatus.WRONG).build(),
                Answer.builder().answerId("3").answerStatus(AnswerStatus.WRONG).build());
    }
}
