package com.practice.notepad.enums;

public class CalculateApp {
    public static void main(String[] args) {
        method1();
        method2();
    }

    static void method1() {
        Operator.PLUS.printCalculator();
        Operator.MINUS.printCalculator();
    }

    static void method2() {
        Operator.PLUS.printCalculator();
        Operator.MINUS.printCalculator();
    }
}
