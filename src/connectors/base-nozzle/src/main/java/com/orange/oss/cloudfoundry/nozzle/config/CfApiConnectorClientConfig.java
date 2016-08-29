package com.orange.oss.cloudfoundry.nozzle.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.orange.oss.cloudfoundry.nozzle.CfApiConnectorClient;
import com.orange.oss.cloudfoundry.nozzle.CfApiConnectorClientImpl;

import cf.dropsonde.spring.boot.FirehoseClientProperties;

@Configuration
public class CfApiConnectorClientConfig {

	private static Logger logger=LoggerFactory.getLogger(CfApiConnectorClientConfig.class.getName());
	
	@Bean
	CfApiConnectorClient cfClient(){
		return new CfApiConnectorClientImpl();
	}
	
	
	@Bean
	@Primary
	FirehoseClientProperties fireHoseClientProperties(
			@Value("${cf.firehose.endpoint}")String endpoint,
			@Value("${cf.firehose.skipTlsValidation}")boolean skipTlsValidation,
			CfApiConnectorClient cfApiConnectorClient
			){
		FirehoseClientProperties props = new FirehoseClientProperties();
		props.setEndpoint(endpoint);
		
		props.setSkipTlsValidation(skipTlsValidation);
		
		//request bearer from cf-connector-api
		logger.info("request auth token from cf-api-connector");
		String token=cfApiConnectorClient.getToken();
		logger.info("found auth token from cf-api-connector!");
		logger.info("token value : {}",token);
		
		props.setAuthToken(token);
		return props;
	}
}
