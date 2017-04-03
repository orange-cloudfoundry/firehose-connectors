package com.orange.oss.cloudfoundry.nozzle.domain;

import java.sql.Timestamp;
import java.util.UUID;

public class CrateEnvelope {

    private String id;
    private String origin;
    private String eventType;
    private String deployment;
    private String job;
    private String idx;
    private String ip;
    private String metricName;
    private String metricUnit;
    private double metricValue;
    private Timestamp timestamp;
    private long httpStart;
    private long httpStop;
    private long httpStartStop;

    public CrateEnvelope() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getHttpStart() {
        return httpStart;
    }

    public void setHttpStart(long httpStart) {
        this.httpStart = httpStart;
    }

    public long getHttpStop() {
        return httpStop;
    }

    public void setHttpStop(long httpStop) {
        this.httpStop = httpStop;
    }

    public long getHttpStartStop() {
        return httpStartStop;
    }

    public void setHttpStartStop(long httpStartStop) {
        this.httpStartStop = httpStartStop;
    }


    @Override
    public String toString() {
        return "CrateEnvelope{" +
                "id='" + id + '\'' +
                ", origin='" + origin + '\'' +
                ", eventType='" + eventType + '\'' +
                ", deployment='" + deployment + '\'' +
                ", job='" + job + '\'' +
                ", idx='" + idx + '\'' +
                ", ip='" + ip + '\'' +
                ", metricName='" + metricName + '\'' +
                ", metricUnit='" + metricUnit + '\'' +
                ", metricValue=" + metricValue +
                ", timestamp=" + timestamp +
                ", httpStart=" + httpStart +
                ", httpStop=" + httpStop +
                ", httpStartStop=" + httpStartStop +
                '}';
    }
}
