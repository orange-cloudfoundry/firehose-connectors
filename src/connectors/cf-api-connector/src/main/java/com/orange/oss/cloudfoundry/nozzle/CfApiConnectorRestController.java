package com.orange.oss.cloudfoundry.nozzle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orange.oss.cloudfoundry.nozzle.config.CfClient;

@RestController
@RequestMapping("/cf-api-connector")

public class CfApiConnectorRestController {
	private static Logger logger=LoggerFactory.getLogger(CfApiConnectorRestController.class.getName());
	
	@Autowired
	CfClient client;
	
	@RequestMapping(method=RequestMethod.GET,value = "/bearer",produces="application/text")
	@ResponseBody
	public String fetchBearer() {
		logger.debug("bearer fetch request");
		String bearer=client.bearerToken();
		logger.debug("bearer is {}",bearer);
		return bearer;
	}		
}