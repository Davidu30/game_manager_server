package com.game_manager_server.service;


import com.game_manager_server.data.dto.AnswerQuestionRequestDto;
import com.game_manager_server.data.dto.AnswerQuestionResponseDto;
import com.game_manager_server.data.model.*;
import com.game_manager_server.exceptions.GameManagerPersistencyException;
import com.game_manager_server.persistency.GameManagerPersistenceFacade;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class GameManagerServiceImpl implements GameManagerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameManagerServiceImpl.class);
    private final GameManagerPersistenceFacade gameManagerPersistence;


    @Override
    public AnswerQuestionResponseDto answerQuestion(AnswerQuestionRequestDto answerQuestionRequest) throws GameManagerPersistencyException {
        LOGGER.debug("Inserting question {} to gameId{} by playerId {} ", answerQuestionRequest.getQuestionId(), answerQuestionRequest.getGameId(), answerQuestionRequest.getUserName());
        int earedPoint = 0;
        Game game = gameManagerPersistence.getGame(answerQuestionRequest.getGameId());
        if(game == null) {
            game = new Game(answerQuestionRequest.getGameId());
        }
        Question question = gameManagerPersistence.getQuestion(answerQuestionRequest.getQuestionId());
        if (question == null) {
            LOGGER.error(String.format("QuestionId %s does not exist", answerQuestionRequest.getQuestionId()));
            throw new GameManagerPersistencyException(String.format("QuestionId %s does not exist", answerQuestionRequest.getQuestionId()));
        }
        game.getQuestions().put(answerQuestionRequest.getQuestionId(), question);

        Answer answer = question.getAnswer(answerQuestionRequest.getAnswerId());
        if(answer == null) {
            LOGGER.error(String.format("AnswerId %s does not exist", answerQuestionRequest.getQuestionId()));
            throw new GameManagerPersistencyException(String.format("AnswerId %s does not exist", answerQuestionRequest.getAnswerId()));
        }

        Player player = game.getPlayers().get(answerQuestionRequest.getUserName());
        if(player == null) {
            player = new Player(answerQuestionRequest.getUserName());

        }
        player.getAnsweredQuestionIds().add(answerQuestionRequest.getQuestionId());

        if(answer.getAnswerStatus().getValue() == 1){
            earedPoint = (int) (Math.random() * (10 -1)) + 1;
            player.addEaredPoints(earedPoint);
        }
        game.getPlayers().put(answerQuestionRequest.getUserName(),player);
        gameManagerPersistence.UpdateGame(game);
        return new AnswerQuestionResponseDto(answer.getAnswerStatus().name(), earedPoint);
    }

    @Override
    public Game getLeaderBoard(String gameId) throws GameManagerPersistencyException {
        return gameManagerPersistence.getGame(gameId);
    }


}
