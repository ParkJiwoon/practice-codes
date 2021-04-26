package com.practice.springtest.privat;

public class Calculator {
    private String owner;

    public Calculator(String owner) {
        this.owner = owner;
    }

    private int add(int a, int b) {
        return a + b;
    }

    private void print() {
        System.out.println("This is private method");
    }
}