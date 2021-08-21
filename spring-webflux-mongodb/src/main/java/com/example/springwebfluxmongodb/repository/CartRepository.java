package com.example.springwebfluxmongodb.repository;

import com.example.springwebfluxmongodb.entity.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
