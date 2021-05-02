package com.game_manager_server.exceptions;

/**
 * This class does throw exception for failed operation
 */
public class GameManagerPersistencyException extends Exception{
    /**
     * @param message The exception message
     */
    public GameManagerPersistencyException(String message) {
        super(message);
    }

    public GameManagerPersistencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
