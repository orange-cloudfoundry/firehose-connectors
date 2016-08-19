package com.orange.oss.cloudfoundry.nozzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InfluxdbNozzleApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfluxdbNozzleApplication.class, args);
	}
}
