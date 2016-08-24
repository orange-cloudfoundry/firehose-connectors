package com.orange.oss.cloudfoundry.nozzle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.crate.config.AbstractCrateConfiguration;
import org.springframework.data.crate.core.mapping.schema.CratePersistentEntitySchemaManager;
import org.springframework.data.crate.core.mapping.schema.SchemaExportOption;
import org.springframework.data.crate.repository.config.EnableCrateRepositories;

import io.crate.client.CrateClient;

@Configuration
@EnableCrateRepositories
public class CrateSpringDataConfig extends AbstractCrateConfiguration {

	@Value("${crate.host}")
	private String host;

	@Value("${crate.port}")
	private int port;

	@Override
	public CrateClient crateClient() {
		return new CrateClient(this.host + ":" + this.port);
	}

	@Bean
	public CratePersistentEntitySchemaManager cratePersistentEntitySchemaManager() throws Exception {
		return new CratePersistentEntitySchemaManager(crateTemplate(), SchemaExportOption.UPDATE); //CREATE_DROP for test
	}

}
