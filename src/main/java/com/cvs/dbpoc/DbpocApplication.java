package com.cvs.dbpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = H2ConsoleAutoConfiguration.class)
public class DbpocApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbpocApplication.class, args);
	}

}
