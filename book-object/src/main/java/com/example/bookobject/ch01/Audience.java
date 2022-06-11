package com.example.bookobject.ch01;

/**
 * 관람객
 * - bag: 소지품
 */
public class Audience {
    private final Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Long buy(Ticket ticket) {
        return this.bag.hold(ticket);
    }
}
