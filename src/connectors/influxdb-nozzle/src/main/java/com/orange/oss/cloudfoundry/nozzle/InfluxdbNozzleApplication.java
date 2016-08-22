package com.orange.oss.cloudfoundry.nozzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cf.dropsonde.spring.boot.EnableFirehoseClient;

@SpringBootApplication
@EnableFirehoseClient
public class InfluxdbNozzleApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfluxdbNozzleApplication.class, args);
	}
}
