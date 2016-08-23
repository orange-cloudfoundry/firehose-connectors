package com.orange.oss.cloudfoundry.nozzle.config;

import org.cloudfoundry.dropsonde.events.Envelope;

public interface Publisher {

	void publishNozzleToConnector(Envelope env);

}