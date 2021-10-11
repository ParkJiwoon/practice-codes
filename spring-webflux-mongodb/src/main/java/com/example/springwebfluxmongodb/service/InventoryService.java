package com.example.springwebfluxmongodb.service;

import com.example.springwebfluxmongodb.entity.Item;
import com.example.springwebfluxmongodb.repository.ItemRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final ReactiveFluentMongoOperations fluentOperations;

    public InventoryService(ItemRepository itemRepository, ReactiveFluentMongoOperations fluentOperations) {
        this.itemRepository = itemRepository;
        this.fluentOperations = fluentOperations;
    }

    public Flux<Item> getInventory() {
        return itemRepository.findAll();
    }

    /**
     * Example 쿼리는 이후에 검색 조건 필드가 추가되어도 쉽게 적용 가능하다
     */
    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        // useAnd 가 false 인데 나머지 조건도 null 이면 에러 발생해서 미리 검사함
        if (validateParams(name, description, useAnd)) return Flux.empty();

        Item item = new Item(name, description, 0.0);

        // matchingAll 은 조건 모두 일치, matchingAny 은 하나라도 일치
        ExampleMatcher matcher = (useAnd
                ? ExampleMatcher.matchingAll()
                : ExampleMatcher.matchingAny())
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                                .withIgnoreCase()
                                .withIgnorePaths("price");

        Example<Item> probe = Example.of(item, matcher);
        return itemRepository.findAll(probe);
    }

    /**
     * ReactiveFluentMongoOperations 기본 사용법
     * 비어있는 필드나 부분 일치 (contains) 기능은 사용할 수 없음
     * 몽고디비 쿼리와 같음 => { $and: [ { name: 'TV tray' }, { description: 'Smurf' } ] }
     */
    public Flux<Item> searchByFluentExample(String name, String description) {
        return fluentOperations.query(Item.class)
                .matching(query(where("TV tray").is(name).and("Smurf").is(description)))
                .all();
    }

    /**
     * Example 과도 혼합해서 다양한 쿼리를 만들 수 있음
     */
    public Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {
        if (validateParams(name, description, useAnd)) return Flux.empty();

        Item item = new Item(name, description, 0.0);

        // matchingAll 은 조건 모두 일치, matchingAny 은 하나라도 일치
        ExampleMatcher matcher = (useAnd
                ? ExampleMatcher.matchingAll()
                : ExampleMatcher.matchingAny())
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");

        return fluentOperations.query(Item.class)
                .matching(query(byExample(Example.of(item, matcher))))
                .all();
    }

    // useAnd 가 false 인데 나머지 조건도 null 이면 에러 발생해서 미리 검사함
    private boolean validateParams(String name, String description, boolean useAnd) {
        return !useAnd && name == null && description == null;
    }
}
