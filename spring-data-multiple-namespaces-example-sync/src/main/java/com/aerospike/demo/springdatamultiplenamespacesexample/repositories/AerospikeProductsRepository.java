package com.aerospike.demo.springdatamultiplenamespacesexample.repositories;

import com.aerospike.demo.springdatamultiplenamespacesexample.annotations.ProductsRepository;
import com.aerospike.demo.springdatamultiplenamespacesexample.objects.Product;
import org.springframework.data.aerospike.repository.AerospikeRepository;

@ProductsRepository
public interface AerospikeProductsRepository extends AerospikeRepository<Product, Integer> {
}
