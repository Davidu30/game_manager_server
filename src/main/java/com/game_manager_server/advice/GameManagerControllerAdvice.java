package com.game_manager_server.advice;

import com.game_manager_server.exceptions.ErrorDto;
import com.game_manager_server.exceptions.GameManagerPersistencyException;
import com.game_manager_server.exceptions.InvalidRequestParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.stream.Collectors;

import static com.game_manager_server.exceptions.ErrorsDescription.*;


@ControllerAdvice
public class GameManagerControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameManagerControllerAdvice.class);

    //Validation request parameters exception, throw from the service
    @ExceptionHandler(value = {InvalidRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleValidationException(InvalidRequestParameterException ex) {
        LOGGER.error("Got application exception while processing request , returning status {}", HttpStatus.BAD_REQUEST.value(), ex);
        return new ErrorDto(INVALID_REQUEST_PARAMETER, ex.getMessage());
    }

    //DateBase operation exception
    @ExceptionHandler(value = {GameManagerPersistencyException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleOperationException(GameManagerPersistencyException ex) {
        LOGGER.error("Got persistence exception while database operation, returning status {}", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex);
        return new ErrorDto(PERSISTENCY_OPERATION_ERROR, ex.getMessage());
    }

    //General server error
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto handleGeneralException(Exception ex) {
        LOGGER.error("Got internal server exception, returning status {}", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex);
        return new ErrorDto(GENERAL_SERVER_ERROR, ex.getMessage());
    }

    //Validation request parameters exception (@valid)
    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        LOGGER.error("Got application exception while processing request, returning status {}", HttpStatus.BAD_REQUEST, ex);
        return new ErrorDto(INVALID_REQUEST_PARAMETER,
                ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> "Field error in field '" + fieldError.getField() + "':" +
                        " rejected value [" + ObjectUtils.nullSafeToString(fieldError.getRejectedValue()) + "]; " +
                        fieldError.getDefaultMessage()).collect(Collectors.toList()));
    }

    @ExceptionHandler(value = HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ErrorDto HttpMessageConversionExceptionHandler(HttpMessageConversionException e) {
        String errMsg = "Couldn't convert HTTP message" + e.getMessage();
        LOGGER.error(errMsg + " Returning status " + HttpStatus.BAD_REQUEST, e);
        return new ErrorDto(BAD_REQUEST, errMsg);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    private ResponseEntity<?> noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        String errMsg = "No handler found for " + e.getHttpMethod() + " " + e.getRequestURL();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(BAD_REQUEST, errMsg));
    }

}
