package com.game_manager_server.service;


import com.game_manager_server.data.dto.AnswerQuestionRequestDto;
import com.game_manager_server.data.dto.AnswerQuestionResponseDto;
import com.game_manager_server.data.model.Game;
import com.game_manager_server.exceptions.GameManagerPersistencyException;

public interface GameManagerService {

    AnswerQuestionResponseDto answerQuestion(AnswerQuestionRequestDto answerQuestionRequest) throws GameManagerPersistencyException;

    Game getLeaderBoard(String gameId) throws GameManagerPersistencyException;

}
