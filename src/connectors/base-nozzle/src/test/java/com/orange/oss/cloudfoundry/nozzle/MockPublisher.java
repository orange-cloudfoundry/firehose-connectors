package com.orange.oss.cloudfoundry.nozzle;

import org.cloudfoundry.dropsonde.events.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockPublisher implements Publisher{

	private static Logger logger=LoggerFactory.getLogger(MockPublisher.class.getName());
	
	@Override
	public void publishNozzleToConnector(Envelope env) {
		logger.info("got Envelope : {}",env);
	}

}


