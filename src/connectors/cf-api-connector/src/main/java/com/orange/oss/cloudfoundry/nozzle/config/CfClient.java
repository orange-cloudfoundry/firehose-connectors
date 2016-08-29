package com.orange.oss.cloudfoundry.nozzle.config;


import org.cloudfoundry.operations.DefaultCloudFoundryOperations;
import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.cloudfoundry.reactor.doppler.ReactorDopplerClient;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;
import org.cloudfoundry.reactor.uaa.ReactorUaaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class CfClient {
	private static Logger logger=LoggerFactory.getLogger(CfClient.class.getName());
	
	@Autowired
	ReactorCloudFoundryClient reactorCfClient;
	
	@Autowired
	DefaultConnectionContext connectionContext;

	@Autowired
	PasswordGrantTokenProvider tokenProvider;
	
	@Autowired
	ReactorCloudFoundryClient cloudFoundryClient;

	@Autowired
	ReactorDopplerClient dopplerClient;

	@Autowired
	ReactorUaaClient uaaClient;
	
	@Autowired
	DefaultCloudFoundryOperations operations;

	
	/**
	 * cf api invocation to retrieve an access token
	 * @return
	 */
	public String authToken() {
		logger.info("get a oauth token");
		
		String token="bearer "+this.tokenProvider
			.getToken(this.connectionContext)
			.block();
		return token;
	
	}

	
}
