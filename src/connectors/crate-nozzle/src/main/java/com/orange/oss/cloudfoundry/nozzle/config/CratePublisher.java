package com.orange.oss.cloudfoundry.nozzle.config;

import javax.annotation.PostConstruct;

import org.cloudfoundry.dropsonde.events.Envelope;
import org.cloudfoundry.dropsonde.events.Envelope.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.orange.oss.cloudfoundry.nozzle.Publisher;

@Component
public class CratePublisher implements Publisher {
	
	private static Logger logger=LoggerFactory.getLogger(CratePublisher.class.getName());
	
	
	@Value("${crate.database}")
	private String database;

	@Value("${crate.host}")	
	private String host;
	
	@Value("${crate.port}")	
	private int port;
	
	
	
	@Autowired
	EnvelopeRepository repository;


	
	/**
	 * publish events to backend
	 * @param env
	 */
	
	public CratePublisher(){

	}
	
	
	@PostConstruct
	public void init(){

	}
	
	
	@Override
	public void publishNozzleToConnector(Envelope env){

		CrateEnvelope e = new CrateEnvelope();
		e.setDeployment(env.deployment);
		e.setEventType(env.eventType.toString());
		e.setIndex(env.index);
		e.setIp(env.ip);
		e.setJob(env.job);

//		e.setMetricName(env.valueMetric.name);
//		e.setMetricUnit(env.valueMetric.unit);
//		e.setMetricValue(env.valueMetric.value);
		e.setOrigin(env.origin);
		e.setTimestamp(env.timestamp);
		
		EventType eventType=env.eventType;
		switch (eventType){
		case ContainerMetric:
			break;
		case CounterEvent:
			break;
		case Error:
			break;
		case HttpStart:
			break;
		case HttpStartStop:
			break;
		case HttpStop:
			break;
		case LogMessage:
			break;
		case ValueMetric: {
			String metricName=env.valueMetric.name;
			String metricUnit=env.valueMetric.unit;
			Double metricValue=env.valueMetric.value;
			
//            builder.addField("metricName",metricName);
//            builder.addField("metricUnit",metricUnit);
//            builder.addField("metricValue",metricValue);
//            builder.addField("timestamp", env.timestamp);
		}
			break;
		default:
			break;
		}
		
		
		this.repository.save(e);
		
		
	}
	
//	@Scheduled(fixedDelay=10000)
//	public void queryPingInflux(){
//		Query query = new Query("SELECT idle FROM cpu", this.database);
//		QueryResult result = influxDB.query(query);
//	}
	

}
