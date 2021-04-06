package com.practice.notepad;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApplication {
    public static void main(String[] args) {
        sortedTest();
        filterTest();
        distinctTest();
    }

    public static void sortedTest() {
        // 1. sorted 는 전부 정렬한 뒤에 다음 스트림으로 진행

        Stream.of(3, 2, 1, 5, 6)
                .peek(e -> System.out.println("before sorted " + e))
                .sorted()
                .peek(e -> System.out.println("before findFirst " + e))
                .findFirst()
                .ifPresent(e -> System.out.println("result: " + e));

        System.out.println();
    }

    public static void filterTest() {
        // 2. filter 는 하나씩 진행

        Stream.of(3, 2, 1, 5, 6)
                .peek(e -> System.out.println("before filter " + e))
                .filter(e -> e > 2)
                .peek(e -> System.out.println("before collect " + e))
                .collect(Collectors.toList());

        System.out.println();
    }

    public static void distinctTest() {
        // 3. distinct 도 하나씩 진행

        Stream.of(4, 4, 5, 5, 6, 6)
                .peek(e -> System.out.println("before distinct " + e))
                .distinct()
                .peek(e -> System.out.println("before collect " + e))
                .collect(Collectors.toList());

        System.out.println();
    }
}
