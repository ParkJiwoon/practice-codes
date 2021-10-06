package com.example.springwebfluxmongodb.repository;

import com.example.springwebfluxmongodb.entity.Item;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface ItemByExampleRepository extends ReactiveQueryByExampleExecutor<Item> {
}
