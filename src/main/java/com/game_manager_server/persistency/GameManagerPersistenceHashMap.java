package com.game_manager_server.persistency;


import com.game_manager_server.data.model.Answer;
import com.game_manager_server.data.model.Game;
import com.game_manager_server.data.model.Player;
import com.game_manager_server.data.model.Question;
import com.game_manager_server.exceptions.GameManagerPersistencyException;
import com.game_manager_server.factory.QuestionsFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GameManagerPersistenceHashMap implements GameManagerPersistenceFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameManagerPersistenceHashMap.class);
    //ConcurrentHashMap<String gameId, Game>
    private ConcurrentHashMap<String, Game> gamesHashMap = new ConcurrentHashMap<>();

    private final HashMap<String,Question> questions;

    public GameManagerPersistenceHashMap(int numOfQuestions) {
        questions = QuestionsFactory.generatesQuestions(numOfQuestions);
    }

    @Override
    public Question getRandomQuestion() throws GameManagerPersistencyException {
        return questions.get(String.valueOf((int) (Math.random() * (questions.size()))));
    }

    @Override
    public Question getQuestion(String questionId) throws GameManagerPersistencyException {
        return questions.get(questionId);
    }

    @Override
    public Game getGame(String gameId) throws GameManagerPersistencyException {
        LOGGER.debug("Receiving game from DB");
        Game result = gamesHashMap.get(gameId);
        LOGGER.debug("Received game {} from DB", result);
        return result;
    }

    @Override
    public void addGame(Game game) throws GameManagerPersistencyException {
        LOGGER.debug("Inserting game {} ", game);
        gamesHashMap.put(game.getGameId(), game);
    }

    @Override
    public void addPlayerGame(Player player, String gameId) throws GameManagerPersistencyException {
        LOGGER.debug("Inserting player {} to gameId{} ",player, gameId);
        Game game = gamesHashMap.get(gameId);
        if(game == null)
            addGame(new Game(gameId));
        gamesHashMap.get(gameId).getPlayers().put(player.getUserName(), player);

    }

    @Override
    public void deleteGame(String gameId) throws GameManagerPersistencyException {
        LOGGER.debug("Deleting gameId {}", gameId);
        gamesHashMap.remove(gameId);
        LOGGER.debug("Deleted gameId {}", gameId);
    }

    @Override
    public List<Game> getAllGames() {
        LOGGER.debug("Receiving all the games from DB");
        List<Game> games = new ArrayList<Game>(gamesHashMap.values());
        LOGGER.debug("Received all the games from DB");
        return games;
    }

    @Override
    public void clearDB() {
        LOGGER.debug("Clearing all the games from DB");
        gamesHashMap.clear();
    }

    @Override
    public void UpdateGame(Game game) throws GameManagerPersistencyException {
        LOGGER.debug("Updating gameId {} to {} ", game.getGameId(), game);
        gamesHashMap.put(game.getGameId(), game);
    }
}




