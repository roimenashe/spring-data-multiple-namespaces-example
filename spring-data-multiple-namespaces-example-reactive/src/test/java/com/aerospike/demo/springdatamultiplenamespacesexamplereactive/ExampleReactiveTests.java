package com.aerospike.demo.springdatamultiplenamespacesexamplereactive;

import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.objects.Consumer;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.objects.Item;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.repositories.ReactiveAerospikeItemsRepository;
import com.aerospike.demo.springdatamultiplenamespacesexamplereactive.repositories.ReactiveAerospikeConsumersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ExampleReactiveTests {

    @Autowired
    ReactiveAerospikeConsumersRepository consumersRepository;
    @Autowired
    ReactiveAerospikeItemsRepository itemsRepository;

    @Test
    public void consumerTest() {
        Consumer consumer = new Consumer(1, "consumer1", "consumer1@gmail.com", 30);
        consumersRepository.save(consumer).block();

        Optional<Consumer> consumerFromDB = consumersRepository.findById(consumer.getId()).blockOptional();
        assertThat(consumerFromDB).hasValue(consumer);
    }

    @Test
    public void itemTest() {
        Item item = new Item(1, "item1", "item1@gmail.com", "iron");
        itemsRepository.save(item).block();

        Optional<Item> itemFromDB = itemsRepository.findById(item.getId()).blockOptional();
        assertThat(itemFromDB).hasValue(item);
    }
}
