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

	private static Logger logger = LoggerFactory.getLogger(InfluxPublisher.class.getName());

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
	 * 
	 * @param env
	 */

	public InfluxPublisher() {

	}

	@PostConstruct
	public void init() {
		this.influxDB = InfluxDBFactory.connect("http://" + this.host + ":" + this.port, this.username, this.password);
		logger.info("creating influxdb database {}",this.database);
		influxDB.createDatabase(this.database);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orange.oss.cloudfoundry.nozzle.config.Publisher#
	 * publishNozzleToConnector(org.cloudfoundry.dropsonde.events.Envelope)
	 */
	@Override
	public void publishNozzleToConnector(Envelope env) {

		EventType eventType = env.eventType;
		switch (eventType) {
		case Error:
			break;
		case HttpStart:
			break;
		case HttpStartStop: //deprecated
			break;
		case HttpStop:
			break;
		case LogMessage:
			break;

		case CounterEvent:
			break;

		case ContainerMetric: {
			saveContainerMetric(env);			
			break;}
		case ValueMetric: {
			saveValueMetric(env);
			break;}
		default:
			logger.error("unknow even type!");
			break;
		}

	}
	/**
	 * save container metric
	 * @param env
	 */
	private void saveContainerMetric(Envelope env) {
		BatchPoints batchPoints = BatchPoints
				.database(this.database)
				.tag("async", "true")
				.retentionPolicy("default")
				.consistency(ConsistencyLevel.ALL)
				.build();

		
		Builder builder = Point.measurement("containerMetric");

		builder.time(env.timestamp, TimeUnit.MILLISECONDS)
//			.addField("origin", env.origin) => rep
//			.addField("eventType", env.eventType.toString()) => containerMetric
			.tag("deployment", env.deployment)
			.tag("job", env.job)
			.tag("index", env.index)
			.tag("ip", env.ip)
			.tag("applicationId",env.containerMetric.applicationId)
			.tag("instanceIndex",env.containerMetric.instanceIndex.toString())
			.addField("cpuPercentage",env.containerMetric.cpuPercentage)
			.addField("diskBytes",env.containerMetric.diskBytes)
			.addField("memoryBytes",env.containerMetric.memoryBytes);
		
		
		
		Point point = builder.build();
		batchPoints.point(point);
		influxDB.write(batchPoints);
		
		logger.debug("save container point {}", point);
	}

	/**
	 * save value metric
	 * @param env
	 */
	private void saveValueMetric(Envelope env) {
		BatchPoints batchPoints = BatchPoints
				.database(this.database)
				.tag("async", "true")
				.retentionPolicy("default")
				.consistency(ConsistencyLevel.ALL)
				.build();

		
		Builder builder = Point.measurement(env.origin);

		builder.time(env.timestamp, TimeUnit.MILLISECONDS)
			//.tag("origin", env.origin)
			.tag("deployment", env.deployment)
			.tag("job", env.job)
			.tag("index", env.index)
			.tag("ip", env.ip)
			.addField(env.valueMetric.name,env.valueMetric.value);
		
		Point point = builder.build();
		batchPoints.point(point);
		influxDB.write(batchPoints);
		
		logger.debug("save value metric point {}", point);
	}

	
	
	@Scheduled(fixedDelay = 10000)
	public void queryPingInflux() {
		Query query = new Query("SELECT idle FROM cpu", this.database);
		QueryResult result = influxDB.query(query);
	}

}
