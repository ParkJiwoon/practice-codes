package com.practice.springkotlin

import javax.annotation.PostConstruct

class MyClass {

    @PostConstruct
    fun setup() {
        print("Hello setup")
    }

    fun print() {
        print("Hello world")
    }
}