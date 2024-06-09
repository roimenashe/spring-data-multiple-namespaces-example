package com.aerospike.demo.springdatamultiplenamespacesexample.configuration;

import com.aerospike.client.Host;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.demo.springdatamultiplenamespacesexample.annotations.UsersRepository;
import com.aerospike.demo.springdatamultiplenamespacesexample.repositories.AerospikeUsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.config.AerospikeSettings;
import org.springframework.data.aerospike.convert.MappingAerospikeConverter;
import org.springframework.data.aerospike.core.AerospikeExceptionTranslator;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.mapping.AerospikeMappingContext;
import org.springframework.data.aerospike.query.QueryEngine;
import org.springframework.data.aerospike.query.cache.IndexRefresher;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.data.aerospike.server.version.ServerVersionSupport;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableAerospikeRepositories(basePackageClasses = AerospikeUsersRepository.class,
        includeFilters = @ComponentScan.Filter(UsersRepository.class),
        aerospikeTemplateRef = "aerospikeTemplateUsers")
public class AerospikeUsersConfiguration extends AbstractAerospikeDataConfiguration {
    @Override
    protected Collection<Host> getHosts() {
        return Collections.singleton(new Host("localhost", 3000));
    }

    @Override
    protected String nameSpace() {
        return "users";
    }

    @Override
    protected ClientPolicy getClientPolicy() {
        ClientPolicy clientPolicy = super.getClientPolicy(); // applying default values first
        clientPolicy.user = "user";
        clientPolicy.password = "password";
        return clientPolicy;
    }

    @Bean(name = "aerospikeTemplateUsers")
    public AerospikeTemplate aerospikeTemplateUsers(IAerospikeClient aerospikeClient,
                                               MappingAerospikeConverter mappingAerospikeConverter,
                                               AerospikeMappingContext aerospikeMappingContext,
                                               AerospikeExceptionTranslator aerospikeExceptionTranslator,
                                               QueryEngine queryEngine, IndexRefresher indexRefresher,
                                               ServerVersionSupport serverVersionSupport, AerospikeSettings settings) {
        return new AerospikeTemplate(aerospikeClient, nameSpace(), mappingAerospikeConverter,
                aerospikeMappingContext, aerospikeExceptionTranslator, queryEngine, indexRefresher,
                serverVersionSupport);
    }
}
