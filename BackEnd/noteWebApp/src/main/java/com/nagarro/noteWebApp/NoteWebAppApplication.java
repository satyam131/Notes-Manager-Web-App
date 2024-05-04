package com.nagarro.noteWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NoteWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteWebAppApplication.class, args);
	}

}
