package com.practice.notepad.enums;

public enum Operator {
    PLUS(new PlusCalculator()),
    MINUS(new MinusCalculator()),
    ;

    private final Calculator calculator;

    Operator(Calculator calculator) {
        this.calculator = calculator;
    }

    int calculate(int a, int b) {
        return calculator.calculate(a, b);
    }

    void printCalculator() {
        System.out.println(calculator);
    }
}
