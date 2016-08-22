package com.orange.oss.cloudfoundry.nozzle.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import cf.dropsonde.firehose.Firehose;
import cf.dropsonde.firehose.FirehoseBuilder;

@Configuration
public class NozzleListener {

	private static Logger logger = LoggerFactory.getLogger(NozzleListener.class.getName());

	@Autowired
	Firehose firehose;

	// public NozzleListener() {
	// try (final Firehose firehose = FirehoseBuilder.create(this.firehoseUrl,
	// this.firehoseBearer)
	// .skipTlsValidation(this.firehoseSkipTlsValidation).build()) {
	//
	// logger.info("url:{}", this.firehoseUrl);
	// logger.info("bearer:" + this.firehoseBearer);
	// logger.info("skipTls:" + this.firehoseSkipTlsValidation);
	//
	// firehose.open().toBlocking().forEach(envelope -> {
	// logger.info(envelope.toString());
	// });
	// }
	// }

	public NozzleListener(){
	}
	
	
	public void startNozzle(){
		logger.info("start nozzle");
		firehose
        .open()
        .toBlocking()
        .forEach(envelope -> {logger.info(envelope.toString());});
	}
	
	
}