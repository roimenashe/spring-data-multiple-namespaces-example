package com.aerospike.demo.springdatamultiplenamespacesexample.repositories;

import com.aerospike.demo.springdatamultiplenamespacesexample.annotations.ConsumersRepository;
import com.aerospike.demo.springdatamultiplenamespacesexample.objects.Consumer;
import org.springframework.data.aerospike.repository.AerospikeRepository;

@ConsumersRepository
public interface AerospikeConsumersRepository extends AerospikeRepository<Consumer, Integer> {
}
