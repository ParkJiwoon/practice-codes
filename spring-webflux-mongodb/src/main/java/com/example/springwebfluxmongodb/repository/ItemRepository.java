package com.example.springwebfluxmongodb.repository;

import com.example.springwebfluxmongodb.entity.Item;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
    Flux<Item> findByNameContaining(String partialName);

    @Query("{ 'name' : ?0, 'age' : ?1 }")
    Flux<Item> findItemsForCustomerMonthlyReport(String name, int age);

    @Query(sort = "{ 'age' : -1 }")
    Flux<Item> findSortedStuffForWeeklyReport();
}
