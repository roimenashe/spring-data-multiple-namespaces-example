package com.aerospike.demo.springdatamultiplenamespacesexample;

import com.aerospike.demo.springdatamultiplenamespacesexample.objects.Product;
import com.aerospike.demo.springdatamultiplenamespacesexample.objects.User;
import com.aerospike.demo.springdatamultiplenamespacesexample.repositories.AerospikeUsersRepository;
import com.aerospike.demo.springdatamultiplenamespacesexample.repositories.AerospikeProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExampleTests {

    @Autowired
    AerospikeUsersRepository aerospikeUsersRepository;
    @Autowired
    AerospikeProductsRepository aerospikeProductsRepository;

    @Test
    public void userTest() {
        User user = new User(1, "user1", "user1@gmail.com", 30);
        aerospikeUsersRepository.save(user);

        Optional<User> userFromDB = aerospikeUsersRepository.findById(user.getId());
        assertThat(userFromDB).hasValue(user);
    }

    @Test
    public void productTest() {
        Product product = new Product(1, "product1", "product1@gmail.com", "iron");
        aerospikeProductsRepository.save(product);

        Optional<Product> productFromDB = aerospikeProductsRepository.findById(product.getId());
        assertThat(productFromDB).hasValue(product);
    }
}
