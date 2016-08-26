package com.orange.oss.cloudfoundry.nozzle;

import org.cloudfoundry.dropsonde.events.Envelope;

public interface Publisher {

	void publishNozzleToConnector(Envelope env);

}