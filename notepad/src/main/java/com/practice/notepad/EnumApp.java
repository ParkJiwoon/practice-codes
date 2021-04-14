package com.practice.notepad;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumApp {
    private enum Fruit {
        APPLE(123),
        BANANA(456),
        ORANGE(789)
        ;

        final private Integer price;

        Fruit(Integer price) {
            this.price = price;
        }

        public Integer getPrice() {
            return price;
        }
    }

    // enum 의 특정 value 들을 리스트로 만들고 싶음
    // enum 자체가 고정되어 있으니 굳이 매 코드에서 Stream 연산을 할 필요 없이 미리 만들어줌
    private static final List<Integer> prices = Arrays.stream(Fruit.values())
                                                      .map(Fruit::getPrice)
                                                      .collect(Collectors.toList());

    public static void main(String[] args) {
        System.out.println(prices);
    }
}

