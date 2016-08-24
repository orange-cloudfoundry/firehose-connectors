package com.orange.oss.cloudfoundry.nozzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import cf.dropsonde.spring.boot.EnableFirehoseClient;

@SpringBootApplication
@EnableFirehoseClient
@EnableScheduling
public class CrateFirehoseClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrateFirehoseClientApplication.class, args);
	}
}