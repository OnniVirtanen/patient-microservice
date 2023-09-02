package com.patient.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the PatientApplication Spring Boot application.
 */
@SpringBootApplication
public class PatientApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(PatientApplication.class, args);
	}

}
