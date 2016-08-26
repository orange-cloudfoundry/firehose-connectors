package com.orange.oss.cloudfoundry.nozzle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cf-api-connector")

public class CfApiConnectorRestController {
	private static Logger logger=LoggerFactory.getLogger(CfApiConnectorRestController.class.getName());
	
	@RequestMapping(method=RequestMethod.GET,value = "/bearer",produces="application/text")
	@ResponseBody
	public String fetchBearer() {
		logger.debug("bearer fetch request");
		return "xxx";
		
	}		
}