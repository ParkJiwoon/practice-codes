package com.example.springwebfluxmongodb.repository;

import com.example.springwebfluxmongodb.entity.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
