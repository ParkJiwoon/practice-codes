package com.example.springwebflux.kitchen;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class KitchenService {

    private static final Random PICKER = new Random();

    private static final List<Dish> MENU = Arrays.asList(
            new Dish("Sesame chicken"),
            new Dish("Lo mein noodles, plain"),
            new Dish("Sweet & sour beef")
    );

    /**
     * 요리 스트림 생성
     */
    Flux<Dish> getDishes() {
        return Flux.<Dish> generate(sink -> sink.next(randomDish()))
                .delayElements(Duration.ofMillis(250));
    }

    /**
     * 요리 무작위 선택
     */
    private Dish randomDish() {
        return MENU.get(PICKER.nextInt(MENU.size()));
    }
}
