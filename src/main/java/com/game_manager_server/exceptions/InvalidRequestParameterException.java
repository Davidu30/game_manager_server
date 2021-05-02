package com.game_manager_server.exceptions;

public class InvalidRequestParameterException extends IllegalArgumentException {

    public InvalidRequestParameterException(String message) {
        super(message);
    }

}
