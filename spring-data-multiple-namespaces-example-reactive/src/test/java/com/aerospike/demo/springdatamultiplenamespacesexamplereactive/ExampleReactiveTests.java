package com.aerospike.demo.springdatamultiplenamespacesexamplereactive;

import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.objects.Item;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.objects.User;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.repositories.AerospikeItemsReactiveRepository;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.repositories.AerospikeUsersReactiveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExampleReactiveTests {

    @Autowired
    AerospikeUsersReactiveRepository aerospikeUsersReactiveRepository;
    @Autowired
    AerospikeItemsReactiveRepository aerospikeItemsReactiveRepository;

    @Test
    public void userTest() {
        User user = new User(1, "user1", "user1@gmail.com", 30);
        aerospikeUsersReactiveRepository.save(user).block();

        Optional<User> userFromDB = aerospikeUsersReactiveRepository.findById(user.getId()).blockOptional();
        assertThat(userFromDB).hasValue(user);
    }

    @Test
    public void itemTest() {
        Item item = new Item(1, "item1", "item1@gmail.com", "iron");
        aerospikeItemsReactiveRepository.save(item).block();

        Optional<Item> productFromDB = aerospikeItemsReactiveRepository.findById(item.getId()).blockOptional();
        assertThat(productFromDB).hasValue(item);
    }
}
