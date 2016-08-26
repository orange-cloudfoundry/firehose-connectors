package com.orange.oss.cloudfoundry.nozzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cf.dropsonde.spring.boot.EnableFirehoseClient;


/**
 * Test Spring Boot app for base nozzle testing
 * @author poblin-orange
 *
 */
@SpringBootApplication
@EnableFirehoseClient
public class TestSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}

}
