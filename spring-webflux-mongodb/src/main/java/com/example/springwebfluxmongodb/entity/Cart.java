package com.example.springwebfluxmongodb.entity;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private @Id String id;
    private List<CartItem> cartItems;

    private Cart() {}

    public Cart(String id) {
        this(id, new ArrayList<>());
    }

    public Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }
}
