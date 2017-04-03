package com.orange.oss.cloudfoundry.nozzle.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnvelopeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CrateEnvelope> findAll() {
        List<CrateEnvelope> result = jdbcTemplate.query(
                "SELECT id, origin, eventType, deployment, job, idx, ipaddress, metricName, metricUnit, metricValue, timestamp, httpStart, httpStop, httpStartStop FROM enveloppe",
                (rs, rowNum) -> {
                    CrateEnvelope e = new CrateEnvelope();
                    e.setId(rs.getString("id"));
                    e.setOrigin(rs.getString("origin"));
                    e.setEventType(rs.getString("eventType"));
                    e.setDeployment(rs.getString("deployment"));
                    e.setJob(rs.getString("job"));
                    e.setIdx(rs.getString("idx"));
                    e.setIp(rs.getString("ipaddress"));
                    e.setMetricName(rs.getString("metricName"));
                    e.setMetricUnit(rs.getString("metricUnit"));
                    e.setMetricValue(rs.getDouble("metricValue"));
                    e.setTimestamp(rs.getTimestamp("timestamp"));
                    e.setHttpStart(rs.getLong("httpStart"));
                    e.setHttpStop(rs.getLong("httpStop"));
                    e.setHttpStartStop(rs.getLong("httpStartStop"));
                    return e;
                }
        );
        return result;
    }

    public void save(CrateEnvelope e) {
        jdbcTemplate.update("INSERT INTO enveloppe(id, origin, eventType, deployment, job, idx, ipaddress, metricName, metricUnit, metricValue, timestamp, httpStart, httpStop, httpStartStop) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                e.getId(),
                e.getOrigin(),
                e.getEventType(),
                e.getDeployment(),
                e.getJob(),
                e.getIdx(),
                e.getIp(),
                e.getMetricName(),
                e.getMetricUnit(),
                e.getMetricValue(),
                e.getTimestamp(),
                e.getHttpStart(),
                e.getHttpStop(),
                e.getHttpStartStop());
    }
}
