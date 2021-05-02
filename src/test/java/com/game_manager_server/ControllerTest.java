package com.game_manager_server;

import com.game_manager_server.config.GameManagerServerConfiguration;
import com.game_manager_server.data.dto.AnswerQuestionRequestDto;
import com.game_manager_server.data.dto.GetLeaderboardRequestDto;
import com.game_manager_server.data.model.Game;
import com.game_manager_server.data.model.Player;
import com.game_manager_server.data.model.Question;
import com.game_manager_server.exceptions.GameManagerPersistencyException;
import com.game_manager_server.persistency.GameManagerPersistenceFacade;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@SpringBootTest(value = "appName=gameManager", classes = GameManagerServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ContextConfiguration(classes = GameManagerServerConfiguration.class)
public class ControllerTest {

    @Value("${server.port}")
    private int serverPort;

    @Autowired
    GameManagerPersistenceFacade gameManagerPersistenceFacade;


    @Before
    public void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = serverPort;
    }

    //stage 1 tests
    @Test
    public void answerQuestionNotExistGameIdTest() {
        String url = "/v1/answerQuestion";
        AnswerQuestionRequestDto answerQuestionTest = AnswerQuestionRequestDto.builder().gameId("NotExist").userName("userNameTest").answerId("0").questionId("3").build();
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(answerQuestionTest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("answerStatus", equalTo("RIGHT answer"))
                .body("pointsEarned", notNullValue());
    }

    @Test
    public void answerQuestionEmptyGameIdTest() {
        String url = "/v1/answerQuestion";
        AnswerQuestionRequestDto answerQuestionTest = AnswerQuestionRequestDto.builder().gameId("").userName("userNameTest").answerId("0").questionId("3").build();
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(answerQuestionTest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorCode", equalTo(1001))
                .body("errorReason", equalTo("Invalid request parameter"))
                .body("description", notNullValue());
    }

    @Test
    public void answerQuestionEmptyUserNameTest() {
        String url = "/v1/answerQuestion";
        AnswerQuestionRequestDto answerQuestionTest = AnswerQuestionRequestDto.builder().gameId("155").userName("").answerId("0").questionId("3").build();
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(answerQuestionTest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorCode", equalTo(1001))
                .body("errorReason", equalTo("Invalid request parameter"))
                .body("description", notNullValue());
    }

    @Test
    public void answerQuestionEmptyAnswerIdTest() {
        String url = "/v1/answerQuestion";
        AnswerQuestionRequestDto answerQuestionTest = AnswerQuestionRequestDto.builder().gameId("1234").userName("userNameTest").answerId("").questionId("3").build();
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(answerQuestionTest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorCode", equalTo(1001))
                .body("errorReason", equalTo("Invalid request parameter"))
                .body("description", notNullValue());
    }

    @Test
    public void answerQuestionEmptyQuestionIdTest() {
        String url = "/v1/answerQuestion";
        AnswerQuestionRequestDto answerQuestionTest = AnswerQuestionRequestDto.builder().gameId("1234").userName("userNameTest").answerId("0").questionId("").build();
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(answerQuestionTest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorCode", equalTo(1001))
                .body("errorReason", equalTo("Invalid request parameter"))
                .body("description", notNullValue());
    }

    @Test
    public void answerQuestionNotExistQuestionIdTest() {
        String url = "/v1/answerQuestion";
        AnswerQuestionRequestDto answerQuestionTest = AnswerQuestionRequestDto.builder().gameId("1234").userName("userNameTest").answerId("0").questionId("notExist").build();
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(answerQuestionTest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body("errorCode", equalTo(1002))
                .body("errorReason", equalTo("Persistence operation error"))
                .body("description", notNullValue());
    }

    @Test
    public void answerQuestionNotExistAnswerIdTest() {
        String url = "/v1/answerQuestion";
        AnswerQuestionRequestDto answerQuestionTest = AnswerQuestionRequestDto.builder().gameId("1234").userName("userNameTest").answerId("9").questionId("2").build();
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(answerQuestionTest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body("errorCode", equalTo(1002))
                .body("errorReason", equalTo("Persistence operation error"))
                .body("description", notNullValue());
    }

    @Test
    public void answerQuestionInvalidBodyTest() {
        String url = "/v1/answerQuestion";
        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body("{\"userName\": \"1234\", \"gameId\":10, \"answerId\":0, \"questionId\":2")
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorCode", equalTo(1003))
                .body("errorReason", equalTo("Invalid request"))
                .body("description", notNullValue());
    }

    @Test
    public void getLeaderboardTest() throws GameManagerPersistencyException {
        String url = "/v1/getLeaderboard";
        String gameId = "12345";
        GetLeaderboardRequestDto getLeaderboardRequest = GetLeaderboardRequestDto.builder().gameId(gameId).build();
        Game game = Game.builder().gameId(gameId).players(new HashMap<String, Player>() {{
            put("player1",new Player("player1"));
            put("player2",new Player("player2"));
        }}).questions(new HashMap<String, Question>() {{
            put("1", new Question("1"));
        }}).build();
        gameManagerPersistenceFacade.addGame(game);

        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(getLeaderboardRequest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body("gameId", equalTo(gameId))
                .body("players", notNullValue())
                .body("questions", notNullValue());
    }

    @Test
    public void getLeaderboardNotExistGameTest() throws GameManagerPersistencyException {
        String url = "/v1/getLeaderboard";
        String gameId = "12345";
        GetLeaderboardRequestDto getLeaderboardRequest = GetLeaderboardRequestDto.builder().gameId(gameId).build();

        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(getLeaderboardRequest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo("The gameId " + gameId + " does not exist"));
    }

    @Test
    public void getLeaderboardEmptyGameIdTest() throws GameManagerPersistencyException {
        String url = "/v1/getLeaderboard";
        String gameId = "";
        GetLeaderboardRequestDto getLeaderboardRequest = GetLeaderboardRequestDto.builder().gameId(gameId).build();

        given()
                .header("Content-Type", ContentType.JSON)
                .header("Accept", ContentType.JSON)
                .body(getLeaderboardRequest)
                .when()
                .post(url)
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("errorCode", equalTo(1001))
                .body("errorReason", equalTo("Invalid request parameter"))
                .body("description", notNullValue());
    }
}
