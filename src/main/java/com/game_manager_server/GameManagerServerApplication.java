package com.game_manager_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameManagerServerApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(GameManagerServerApplication.class);

	public static void main(String[] args) {
		LOGGER.debug("=============== GameManager server is starting... ===============");

		SpringApplication.run(GameManagerServerApplication.class, args);

		System.out.println("=============== GameManager Server is UP & RUNNING ===============");
	}

}
