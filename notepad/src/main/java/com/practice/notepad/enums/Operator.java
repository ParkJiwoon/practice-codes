package com.practice.notepad.enums;

import java.util.Arrays;

public enum Operator {
    PLUS(new PlusCalculator(), "더하기"),
    MINUS(new MinusCalculator(), "빼기"),
    ;

    private final Calculator calculator;
    private final String krName;

    Operator(Calculator calculator, String krName) {
        this.calculator = calculator;
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }

    public int calculate(int a, int b) {
        return calculator.calculate(a, b);
    }

    public void printCalculator() {
        System.out.println(calculator);
    }

    public String concat(String str) {
        return name().concat(str);
    }

    public static String translateEnToKr1(String enName) {
        try {
            return valueOf(enName).krName;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String translateEnToKr2(String enName) {
        return Arrays.stream(values())
                .filter(operator -> operator.name().equals(enName))
                .findAny()
                .map(operator -> operator.krName)
                .orElse(null);
    }
}
