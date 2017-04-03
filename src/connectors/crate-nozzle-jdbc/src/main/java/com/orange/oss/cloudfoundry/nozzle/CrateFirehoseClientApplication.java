package com.orange.oss.cloudfoundry.nozzle;

import cf.dropsonde.spring.boot.EnableFirehoseClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableFirehoseClient
//@EnableScheduling
public class CrateFirehoseClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrateFirehoseClientApplication.class, args);
	}
}