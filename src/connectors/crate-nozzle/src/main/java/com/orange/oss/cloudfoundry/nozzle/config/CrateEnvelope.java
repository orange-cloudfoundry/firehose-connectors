package com.orange.oss.cloudfoundry.nozzle.config;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.crate.core.mapping.annotations.Table;

@Table(name = "envelope", numberOfReplicas = "3") // , refreshInterval="1500",
													// columnPolicy="dynamic")
public class CrateEnvelope {

	
	@PersistenceConstructor
    public CrateEnvelope() {
        this.id = UUID.randomUUID().toString();
    }	
	
	
	@Id
	private String id;

	public String getId() {
		return id;
	}

	private String origin;
	private String eventType;
	private String deployment;
	private String job;
	private String index;
	private String ip;
	private String metricName;
	private String metricUnit;
	private double metricValue;
	private long timestamp;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getDeployment() {
		return deployment;
	}

	public void setDeployment(String deployment) {
		this.deployment = deployment;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public String getMetricUnit() {
		return metricUnit;
	}

	public void setMetricUnit(String metricUnit) {
		this.metricUnit = metricUnit;
	}

	public double getMetricValue() {
		return metricValue;
	}

	public void setMetricValue(double metricValue) {
		this.metricValue = metricValue;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
