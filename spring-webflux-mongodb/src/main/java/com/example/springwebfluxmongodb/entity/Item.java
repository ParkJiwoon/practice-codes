package com.example.springwebfluxmongodb.entity;

import org.springframework.data.annotation.Id;

public class Item {

    private @Id String id;  // 몽고디비의 ObjectId
    private String name;
    private double price;

    private Item() {}

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
