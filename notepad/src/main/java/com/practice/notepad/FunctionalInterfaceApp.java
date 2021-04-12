package com.practice.notepad;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.*;

@Slf4j
public class FunctionalInterfaceApp {
    public static void main(String[] args) {
        IntPredicate intPredicate;

    }

    public void exampleCustom() {
        CustomInterface<String> customInterface = () -> "Hello Custom";

        // abstract method
        String s = customInterface.myCall();
        System.out.println(s);

        // default method
        customInterface.printDefault();

        // static method
        CustomInterface.printStatic();
    }

    public void examplePredicate() {
        // Predicate : T -> boolean
        Predicate<String> predicate = (str) -> str.isBlank();
        boolean result = predicate.test("Hello Predicate");
        System.out.println(result);
    }

    public void exampleConsumer() {
        // Consumer : T -> void
        Consumer<String> consumer = (str) -> System.out.println("Hello " + str);
        consumer.accept("Consumer");
    }

    public void exampleSupplier() {
        // Supplier : () -> T
        Supplier<String> supplier = () -> "Hello Supplier";
        String result = supplier.get();
        System.out.println(result);
    }

    public void exampleFunction() {
        // Function : T -> R
        Function<String, String> function = (integer) -> "Hello " + integer.toString();
        String result = function.apply("Function");
        System.out.println(result);
    }

    public void exampleComparator() {
        // Comparator : (T, T) -> int
        Comparator<String> comparator = (str1, str2) -> 1;
        int compare = comparator.compare("a", "b");
        System.out.println(compare);
    }

    public void exampleRunnable() {
        // Runnable : () -> void
        Runnable runnable = () -> System.out.println("Hello Runnable");
        runnable.run();
    }

    public void exampleCallable() throws Exception {
        // Callable : () -> T
        Callable<String> callable = () -> "Hello Callable";
        String result = callable.call();
        System.out.println(result);
    }

    public void exampleBi() {
        // BiPredicate : (T, U) -> boolean
        BiPredicate<String, Integer> biPredicate;

        // BiConsumer : (T, U) -> void
        BiConsumer<String, Integer> biConsumer;

        // BiFunction : (T, U) -> R
        BiFunction<String, Integer, String> biFunction;
    }
}

@FunctionalInterface
interface CustomInterface<T> {
    // abstract method 오직 하나
    T myCall();

    // default method 는 존재해도 상관없음
    default void printDefault() {
        System.out.println("Hello Default");
    }

    // static method 는 존재해도 상관없음
    static void printStatic() {
        System.out.println("Hello Static");
    }
}