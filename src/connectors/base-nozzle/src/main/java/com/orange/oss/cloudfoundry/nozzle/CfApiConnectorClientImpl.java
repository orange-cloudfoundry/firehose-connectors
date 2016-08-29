package com.orange.oss.cloudfoundry.nozzle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * cf-api-connector consumer
 * @author poblin
 *
 */
public class CfApiConnectorClientImpl implements CfApiConnectorClient  {

	private static Logger logger = LoggerFactory.getLogger(CfApiConnectorClientImpl.class.getName());


	String url="http://127.0.0.1:8081/cf-api-connector/auth-token";

	/* (non-Javadoc)
	 * @see com.orange.oss.cloudfoundry.nozzle.CfApiConnectorClient#getToken()
	 */
	@Override
	public String getToken() {
		RestTemplate restTemplate = new RestTemplate();
		String rep=restTemplate.getForObject(url, String.class);
		return rep;
	}

	
}
