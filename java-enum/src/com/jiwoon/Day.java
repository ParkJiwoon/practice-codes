package com.jiwoon;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Day {
    MON("Monday", 10),
    TUE("Tuesday", 20),
    WED("Wednesday", 30),
    THU("Thursday", 40),
    FRI("Friday", 50),
    SAT("Saturday", 60),
    SUN("Sunday", 70)
    ;

    private final String label;
    private final int number;

    Day(String label, int number) {
        this.label = label;
        this.number = number;
    }

    public String label() {
        return label;
    }

    public int number() {
        return number;
    }

    private static final Map<String, Day> BY_LABEL =
            Stream.of(values()).collect(Collectors.toMap(Day::label, Function.identity()));

    private static final Map<Integer, Day> BY_NUMBER =
            Stream.of(values()).collect(Collectors.toMap(Day::number, Function.identity()));

    public static Day valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static Day valueOfNumber(int number) {
        return BY_NUMBER.get(number);
    }
}
