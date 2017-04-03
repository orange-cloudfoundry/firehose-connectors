package com.orange.oss.cloudfoundry.nozzle.domain;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Sebastien Bortolussi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.sleuth.rxjava.schedulers.hook.enabled=false", "admin.user=test", "admin.password=pwd"})
public class EnvelopeRepositoryTest {

    @Autowired
    EnvelopeRepository repository;

    private static List<CrateEnvelope> getCrateEnvelopeForId(String... id) {
        return Optional.ofNullable(id)
                .map(ids -> Stream.of(ids)
                        .map(ident -> {
                            final CrateEnvelope e = new CrateEnvelope();
                            e.setId(ident);
                            e.setOrigin("origin");
                            e.setEventType("eventType");
                            e.setDeployment("deployment");
                            e.setJob("job");
                            e.setIdx("idx");
                            e.setIp("ipaddress");
                            e.setMetricName("metricName");
                            e.setMetricUnit("metricUnit");
                            e.setMetricValue(1.2);
                            e.setTimestamp(new Timestamp(1299446702));
                            e.setHttpStart(2222);
                            e.setHttpStop(3333);
                            e.setHttpStartStop(4444);
                            return e;
                        })
                        .collect(Collectors.toList()))
                .orElse(Collections.<CrateEnvelope>emptyList());
    }

    @Ignore
    @Test
    public void findAll() throws Exception {
        getCrateEnvelopeForId("1", "2", "3").stream()
                .forEach(repository::save);
        Assertions.assertThat(repository.findAll()).hasSize(3);

    }

    @Test
    public void save() throws Exception {
        final CrateEnvelope e = getCrateEnvelopeForId("1").get(0);
        repository.save(e);
        final List<CrateEnvelope> all = repository.findAll();
        System.out.println(all);
    }

}