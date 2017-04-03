package com.orange.oss.cloudfoundry.nozzle.service;

import com.orange.oss.cloudfoundry.nozzle.Publisher;
import com.orange.oss.cloudfoundry.nozzle.domain.CrateEnvelope;
import com.orange.oss.cloudfoundry.nozzle.domain.EnvelopeRepository;
import org.cloudfoundry.dropsonde.events.Envelope;
import org.cloudfoundry.dropsonde.events.Envelope.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CratePublisher implements Publisher {

    private static Logger logger = LoggerFactory.getLogger(CratePublisher.class.getName());

    @Autowired
    EnvelopeRepository repository;


    /**
     * publish events to backend
     *
     */

    public CratePublisher() {

    }


    @Override
    public void publishNozzleToConnector(Envelope env) {

        CrateEnvelope e = new CrateEnvelope();
        e.setDeployment(env.deployment);
        e.setEventType(env.eventType.toString());
        e.setIdx(env.index);
        e.setIp(env.ip);
        e.setJob(env.job);

        if (env.valueMetric != null) {
            e.setMetricName(env.valueMetric.name);
            e.setMetricUnit(env.valueMetric.unit);
            e.setMetricValue(env.valueMetric.value);
        }

        e.setOrigin(env.origin);
        e.setTimestamp(new Timestamp(env.timestamp));

        EventType eventType = env.eventType;
        switch (eventType) {
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
                String metricName = env.valueMetric.name;
                String metricUnit = env.valueMetric.unit;
                Double metricValue = env.valueMetric.value;

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


}
