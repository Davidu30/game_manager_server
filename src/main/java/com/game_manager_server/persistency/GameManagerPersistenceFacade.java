package com.game_manager_server.persistency;


import com.game_manager_server.data.model.Game;
import com.game_manager_server.data.model.Player;
import com.game_manager_server.data.model.Question;
import com.game_manager_server.exceptions.GameManagerPersistencyException;

import java.util.List;

public interface GameManagerPersistenceFacade {

    Question getRandomQuestion() throws GameManagerPersistencyException;
    Question getQuestion(String questionId) throws GameManagerPersistencyException;

    Game getGame(String gameId) throws GameManagerPersistencyException;
    void addGame(Game game) throws GameManagerPersistencyException;
    void UpdateGame(Game game) throws GameManagerPersistencyException;
    public void addPlayerGame(Player player, String gameId) throws GameManagerPersistencyException;

    void deleteGame(String gameId) throws GameManagerPersistencyException;

    List<Game> getAllGames();

    void clearDB();

}
