package com.game_manager_server.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game_manager_server.persistency.GameManagerPersistenceFacade;
import com.game_manager_server.persistency.GameManagerPersistenceHashMap;
import com.game_manager_server.service.GameManagerService;
import com.game_manager_server.service.GameManagerServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameManagerServerConfiguration {

    @Value("${gameManager.numOfGenerateQuestion:10}")
    private int numOfGenerateQuestion;

    @Bean
    public GameManagerService gameManagerService() {
        return new GameManagerServiceImpl(GameManagerPersistenceFacade());
    }

    @Bean
    public GameManagerPersistenceFacade GameManagerPersistenceFacade () {
        return new GameManagerPersistenceHashMap(numOfGenerateQuestion);
    }

    @Bean
    public ObjectMapper jsonMapper() {
        ObjectMapper JSON_MAPPER = new ObjectMapper();
        JSON_MAPPER.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        JSON_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JSON_MAPPER.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        JSON_MAPPER.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        return JSON_MAPPER;
    }
}
