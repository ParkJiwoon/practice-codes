package com.example.springwebfluxmongodb.loader;

import com.example.springwebfluxmongodb.entity.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;

@Component
public class TemplateDatabaseLoader {

    @Bean
    public CommandLineRunner initialize(ReactiveMongoOperations mongo) {
        return args -> {
            mongo.save(new Item("Alf alarm clock", 19.99));
            mongo.save(new Item("Smurf TV tray", 24.99));
        };
    }
}
