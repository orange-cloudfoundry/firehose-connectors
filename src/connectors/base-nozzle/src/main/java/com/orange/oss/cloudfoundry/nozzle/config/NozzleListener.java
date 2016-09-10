package com.orange.oss.cloudfoundry.nozzle.config;

import org.cloudfoundry.dropsonde.events.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.orange.oss.cloudfoundry.nozzle.CfApiConnectorClient;
import com.orange.oss.cloudfoundry.nozzle.Publisher;

import cf.dropsonde.firehose.Firehose;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import rx.Observable;

@Configuration
public class NozzleListener {

	private static Logger logger = LoggerFactory.getLogger(NozzleListener.class.getName());

	@Autowired
	Firehose firehose;
	
	@Autowired
	CfApiConnectorClient cfApiConnector; 
	
	
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
		
		try {
        observable
        .toBlocking()
        .forEach(envelope -> {readEnvelope(envelope);});
		} catch (WebSocketHandshakeException e){
			logger.error("rejected Firehose auth token", e);
			logger.error("===> TERMINATING nozzle instance");
			System.exit(-1);
			
		}
	}
	/**
	 * envelope processing
	 * @param envelope
	 */
	private void readEnvelope(Envelope envelope) {
		logger.debug(envelope.toString());
//		if ((EventType.CounterEvent.toString().equals(envelope.eventType))
//			&& ("TruncatingBuffer.DroppedMessages".equals(envelope.valueMetric.name))
//			&& ("doppler".equals(envelope.origin))
//			){
//			Double value=envelope.valueMetric.value;
//			logger.warn("====> doppler dropped messages: {}",value);
//		}
		
		
		this.publisher.publishNozzleToConnector(envelope);
	}
	
	

	
}