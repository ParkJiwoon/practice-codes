package com.example.springwebfluxmongodb.repository;

import com.example.springwebfluxmongodb.entity.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * ReactiveCrudRepository 대신 CrudRepository 를 상속받음
 * 테스트를 위한 데이터 세팅용
 * 비동기로 하면 데이터가 세팅되기 전에 테스트가 먼저 실행되는 이슈가 발생할 수 있음
 */
public interface BlockingItemRepository extends CrudRepository<Item, String> {
}
