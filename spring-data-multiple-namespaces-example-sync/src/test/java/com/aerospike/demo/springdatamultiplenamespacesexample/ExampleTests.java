package com.aerospike.demo.springdatamultiplenamespacesexample;

import com.aerospike.demo.springdatamultiplenamespacesexample.objects.Consumer;
import com.aerospike.demo.springdatamultiplenamespacesexample.objects.Product;
import com.aerospike.demo.springdatamultiplenamespacesexample.repositories.AerospikeConsumersRepository;
import com.aerospike.demo.springdatamultiplenamespacesexample.repositories.AerospikeProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExampleTests {

    @Autowired
    AerospikeConsumersRepository aerospikeUsersRepository;
    @Autowired
    AerospikeProductsRepository aerospikeProductsRepository;

    @Test
    public void consumerTest() {
        Consumer consumer = new Consumer(1, "consumer1", "consumer1@gmail.com", 30);
        aerospikeUsersRepository.save(consumer);

        Optional<Consumer> userFromDB = aerospikeUsersRepository.findById(consumer.getId());
        assertThat(userFromDB).hasValue(consumer);
    }

    @Test
    public void productTest() {
        Product product = new Product(1, "product1", "product1@gmail.com", "iron");
        aerospikeProductsRepository.save(product);

        Optional<Product> productFromDB = aerospikeProductsRepository.findById(product.getId());
        assertThat(productFromDB).hasValue(product);
    }
}
