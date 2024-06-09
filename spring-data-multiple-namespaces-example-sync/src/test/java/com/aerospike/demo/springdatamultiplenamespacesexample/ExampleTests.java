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
    AerospikeUsersRepository usersRepository;
    @Autowired
    AerospikeProductsRepository productsRepository;

    @Test
    public void userTest() {
        User user = new User(1, "user1", "user1@gmail.com", 30);
        usersRepository.save(user);

        Optional<User> userFromDB = usersRepository.findById(user.getId());
        assertThat(userFromDB).hasValue(user);
    }

    @Test
    public void productTest() {
        Product product = new Product(1, "product1", "product1@gmail.com", "iron");
        productsRepository.save(product);

        Optional<Product> productFromDB = productsRepository.findById(product.getId());
        assertThat(productFromDB).hasValue(product);
    }
}
