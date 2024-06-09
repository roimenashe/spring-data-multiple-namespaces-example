package com.aerospike.demo.springdatamultiplenamespacesexample.repositories;

import com.aerospike.demo.springdatamultiplenamespacesexample.annotations.UsersRepository;
import com.aerospike.demo.springdatamultiplenamespacesexample.objects.User;
import org.springframework.data.aerospike.repository.AerospikeRepository;

@UsersRepository
public interface AerospikeUsersRepository extends AerospikeRepository<User, Integer> {
}
