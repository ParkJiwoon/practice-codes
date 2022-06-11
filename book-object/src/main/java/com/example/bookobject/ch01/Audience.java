package com.example.bookobject.ch01;

/**
 * 관람객
 * - bag: 소지품
 */
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }
}
