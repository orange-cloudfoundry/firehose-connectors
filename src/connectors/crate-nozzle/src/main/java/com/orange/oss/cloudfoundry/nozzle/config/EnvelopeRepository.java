package com.orange.oss.cloudfoundry.nozzle.config;

import org.springframework.data.crate.repository.CrateRepository;
import com.orange.oss.cloudfoundry.nozzle.config.CrateEnvelope;

public interface EnvelopeRepository extends CrateRepository<CrateEnvelope,Integer> {
}
