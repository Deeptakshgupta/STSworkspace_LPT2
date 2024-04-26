package com.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;

@SpringBootApplication

public class SessionManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionManagementApplication.class, args);
	}

}
