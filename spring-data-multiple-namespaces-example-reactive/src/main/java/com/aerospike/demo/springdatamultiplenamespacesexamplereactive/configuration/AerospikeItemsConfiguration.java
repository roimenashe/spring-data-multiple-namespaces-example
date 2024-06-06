package com.aerospike.demo.springdatamultiplenamespacesexamplereactive.configuration;

import com.aerospike.client.Host;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.async.EventLoops;
import com.aerospike.client.async.NioEventLoops;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.reactor.IAerospikeReactorClient;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.annotations.ItemsReactiveRepository;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.repositories.AerospikeItemsReactiveRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.config.AbstractReactiveAerospikeDataConfiguration;
import org.springframework.data.aerospike.config.AerospikeSettings;
import org.springframework.data.aerospike.convert.MappingAerospikeConverter;
import org.springframework.data.aerospike.core.AerospikeExceptionTranslator;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.aerospike.core.ReactiveAerospikeTemplate;
import org.springframework.data.aerospike.mapping.AerospikeMappingContext;
import org.springframework.data.aerospike.query.QueryEngine;
import org.springframework.data.aerospike.query.ReactorQueryEngine;
import org.springframework.data.aerospike.query.cache.IndexRefresher;
import org.springframework.data.aerospike.query.cache.ReactorIndexRefresher;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.data.aerospike.server.version.ServerVersionSupport;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableAerospikeRepositories(basePackageClasses = AerospikeItemsReactiveRepository.class,
        includeFilters = @ComponentScan.Filter(ItemsReactiveRepository.class),
        aerospikeTemplateRef = "aerospikeReactiveTemplateItems")
public class AerospikeItemsConfiguration extends AbstractReactiveAerospikeDataConfiguration {
    @Override
    protected Collection<Host> getHosts() {
        return Collections.singleton(new Host("localhost", 3000));
    }

    @Override
    protected String nameSpace() {
        return "items";
    }

    @Override
    protected ClientPolicy getClientPolicy() {
        ClientPolicy clientPolicy = super.getClientPolicy(); // applying default values first
        clientPolicy.user = "tester";
        clientPolicy.password = "psw";
        return clientPolicy;
    }

    @Override
    protected EventLoops eventLoops() {
        return new NioEventLoops(2);
    }

    @Bean(name = "aerospikeReactiveTemplateItems")
    public ReactiveAerospikeTemplate aerospikeReactiveTemplateItems(MappingAerospikeConverter mappingAerospikeConverter,
                                                                    AerospikeMappingContext aerospikeMappingContext,
                                                                    AerospikeExceptionTranslator aerospikeExceptionTranslator,
                                                                    IAerospikeReactorClient aerospikeReactorClient,
                                                                    ReactorQueryEngine reactorQueryEngine,
                                                                    ReactorIndexRefresher reactorIndexRefresher,
                                                                    ServerVersionSupport serverVersionSupport,
                                                                    AerospikeSettings settings) {
        return new ReactiveAerospikeTemplate(aerospikeReactorClient, nameSpace(),
                mappingAerospikeConverter, aerospikeMappingContext, aerospikeExceptionTranslator,
                reactorQueryEngine, reactorIndexRefresher, serverVersionSupport);
    }
}
