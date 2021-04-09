package com.practice.notepad;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceApp {
    public static void main(String[] args) {
        // Predicate : T -> boolean
        Predicate<String> predicate = (str) -> true;
        boolean predicateResult = predicate.test("Hello Predicate");
        System.out.println(predicateResult);

        // Consumer : T -> void
        Consumer<String> consumer = (str) -> System.out.println("Hello " + str);
        consumer.accept("Consumer");

        // Supplier : () -> T
        Supplier<String> supplier = () -> "Hello Supplier";
        String supplyResult = supplier.get();
        System.out.println(supplyResult);

        // Function : T -> R

        // Runnable : () -> void

        // Callable : () -> T
    }
}
