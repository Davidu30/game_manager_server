package com.game_manager_server.controller.v1;


import com.game_manager_server.data.dto.*;
import com.game_manager_server.data.model.Game;
import com.game_manager_server.exceptions.GameManagerPersistencyException;
import com.game_manager_server.service.GameManagerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class GameManagerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameManagerController.class);

    private final GameManagerService gameManagerService;

    @PostMapping("answerQuestion")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> reportDailyWorkingHours(@RequestBody @Valid AnswerQuestionRequestDto answerQuestionRequestDto) throws IllegalArgumentException, GameManagerPersistencyException {
        LOGGER.debug("Got request {}", answerQuestionRequestDto);
        AnswerQuestionResponseDto answerQuestionResponse = gameManagerService.answerQuestion(answerQuestionRequestDto);

        LOGGER.debug("Sending a response {}" , answerQuestionResponse);
        return ResponseEntity.ok(answerQuestionResponse);
    }

    @PostMapping("getLeaderboard")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getUserReport(@RequestBody @Valid GetLeaderboardRequestDto getLeaderboardRequest) throws IllegalArgumentException, GameManagerPersistencyException {
        LOGGER.debug("Got request {}", getLeaderboardRequest);
        Game game = gameManagerService.getLeaderBoard(getLeaderboardRequest.getGameId());
        if(game == null) {
            String response = String.format("The gameId %s does not exist", getLeaderboardRequest.getGameId());
            LOGGER.debug("Sending a response {}" , response);
            return ResponseEntity.ok(response);
        }
        LOGGER.debug("Sending a response {}" , game);
        return ResponseEntity.ok(game);

    }

}
