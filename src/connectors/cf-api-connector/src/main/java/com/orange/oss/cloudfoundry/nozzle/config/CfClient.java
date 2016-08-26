package com.orange.oss.cloudfoundry.nozzle.config;

import org.cloudfoundry.client.v2.organizations.ListOrganizationsRequest;
import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.TokenProvider;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.cloudfoundry.reactor.doppler.ReactorDopplerClient;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;
import org.cloudfoundry.reactor.uaa.ReactorUaaClient;
import org.cloudfoundry.reactor.uaa.tokens.ReactorTokens;
import org.cloudfoundry.uaa.tokens.RefreshTokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class CfClient {

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

	public String bearerToken() {
		String bearer="xx";
		
		this.uaaClient.tokens();
		
		
		return bearer;
	}

	
}
