package com.orange.oss.cloudfoundry.nozzle.config;

import org.cloudfoundry.dropsonde.events.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.orange.oss.cloudfoundry.nozzle.Publisher;

import cf.dropsonde.firehose.Firehose;
import rx.Observable;

@Configuration
public class NozzleListener {

	private static Logger logger = LoggerFactory.getLogger(NozzleListener.class.getName());

	@Autowired
	Firehose firehose;
	
	
	@Autowired
	Publisher publisher;
	
	/**
	 * Nozzle constructor
	 */
	public NozzleListener(){
	}
	
	@Scheduled(fixedDelay=5000)
	public void startNozzle(){
		logger.info("start nozzle");

		Observable<Envelope> observable = firehose.open();
		//Assert.isTrue(firehose.isConnected(),"not connected to Firehose");
		
        observable.toBlocking()
        .forEach(envelope -> {logger.info(envelope.toString()); this.publisher.publishNozzleToConnector(envelope);});
	}
	
	

	
}