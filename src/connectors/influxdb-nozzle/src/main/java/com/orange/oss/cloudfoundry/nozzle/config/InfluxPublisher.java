package com.orange.oss.cloudfoundry.nozzle.config;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.cloudfoundry.dropsonde.events.Envelope;
import org.cloudfoundry.dropsonde.events.Envelope.EventType;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Point.Builder;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.orange.oss.cloudfoundry.nozzle.Publisher;

@Component
public class InfluxPublisher implements Publisher {
	
	private static Logger logger=LoggerFactory.getLogger(InfluxPublisher.class.getName());
	
	
	@Value("${influxdb.database}")
	private String database;

	@Value("${influxdb.host}")	
	private String host;
	
	@Value("${influxdb.port}")	
	private int port;
	
	@Value("${influxdb.username}")	
	private String username;
	
	@Value("${influxdb.password}")	
	private String password;
	
	private InfluxDB influxDB;

	
	/**
	 * publish events to backend
	 * @param env
	 */
	
	public InfluxPublisher(){

	}
	
	
	@PostConstruct
	public void init(){
		this.influxDB = InfluxDBFactory.connect("http://"+this.host+":"+this.port, this.username, this.password);
		influxDB.createDatabase(this.database);

	}
	
	
	/* (non-Javadoc)
	 * @see com.orange.oss.cloudfoundry.nozzle.config.Publisher#publishNozzleToConnector(org.cloudfoundry.dropsonde.events.Envelope)
	 */
	@Override
	public void publishNozzleToConnector(Envelope env){
		BatchPoints batchPoints = BatchPoints
		                .database(this.database)
		                .tag("async", "true")
		                .retentionPolicy("default")
		                .consistency(ConsistencyLevel.ALL)
		                .build();
		
		Builder builder = Point.measurement("firehose");
		
        builder.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        .addField("origin", env.origin)
        .addField("eventType",env.eventType.toString())
        .addField("deployment", env.deployment)
        .addField("job", env.job)
        .addField("index", env.index)
        .addField("ip", env.ip);
		
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
			
            builder.addField("metricName",metricName);
            builder.addField("metricUnit",metricUnit);
            builder.addField("metricValue",metricValue);
            builder.addField("timestamp", env.timestamp);
		}
			break;
		default:
			break;
		}
		
		
		
        Point point=builder.build();
		batchPoints.point(point);
		influxDB.write(batchPoints);
		
	}
	
	@Scheduled(fixedDelay=10000)
	public void queryPingInflux(){
		Query query = new Query("SELECT idle FROM cpu", this.database);
		QueryResult result = influxDB.query(query);
	}
	

}
