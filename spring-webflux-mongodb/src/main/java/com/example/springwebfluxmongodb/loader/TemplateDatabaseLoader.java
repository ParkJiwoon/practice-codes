package com.example.springwebfluxmongodb.loader;

import com.example.springwebfluxmongodb.entity.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

/**
 * Blocking 레포지토리 대신 MongoOperations 을 사용하여 비동기, 논블로킹 방식으로 데이터 세팅
 */
@Component
public class TemplateDatabaseLoader {

    @Bean
    public CommandLineRunner initialize(MongoOperations mongo) {
        return args -> {
            mongo.findAll(Item.class).stream().findAny().orElseGet(() -> {
                mongo.save(new Item("Alf alarm clock", 19.99));
                mongo.save(new Item("Smurf TV tray", 24.99));
                return null;
            });
        };
    }
}
