package com.orange.oss.cloudfoundry.nozzle.config;

import java.util.concurrent.TimeUnit;

import org.cloudfoundry.dropsonde.events.Envelope;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import cf.dropsonde.firehose.Firehose;
import rx.Observable;

@Configuration
public class NozzleListener {

	private static Logger logger = LoggerFactory.getLogger(NozzleListener.class.getName());

	@Autowired
	Firehose firehose;
	
	
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
	 * Nozzle constructor
	 */
	public NozzleListener(){
		this.influxDB = InfluxDBFactory.connect("http://"+this.host+":1"+this.port, this.username, this.password);
		influxDB.createDatabase(this.database);
	}
	
	@Scheduled(fixedDelay=5000)
	public void startNozzle(){
		logger.info("start nozzle");

		Observable<Envelope> observable = firehose.open();
		//Assert.isTrue(firehose.isConnected(),"not connected to Firehose");
		
        observable.toBlocking()
        .forEach(envelope -> {logger.info(envelope.toString()); this.publishNozzleToConnector(envelope);});
	}
	
	
	/**
	 * publish events to backend
	 * @param env
	 */
	protected void publishNozzleToConnector(Envelope env){
		BatchPoints batchPoints = BatchPoints
		                .database(this.database)
		                .tag("async", "true")
		                .retentionPolicy("default")
		                .consistency(ConsistencyLevel.ALL)
		                .build();
		Point point1 = Point.measurement("firehose")
		                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
		                    .addField("deployment", env.deployment)
		                    .addField("job", env.job)
		                    .addField("index", env.index)
		                    .addField("ip", env.ip)
		                    .addField("timestamp", env.timestamp)
		                    .build();
		batchPoints.point(point1);
		influxDB.write(batchPoints);
		
	}

	@Scheduled(fixedDelay=10000)
	public void queryPingInflux(){
		Query query = new Query("SELECT idle FROM cpu", this.database);
		influxDB.query(query);
	}
	
}