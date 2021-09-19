package com.jiwoon;

import java.util.EnumSet;

public class Main {

    public static void main(String[] args) {
        testText();
    }

    private static void testDay() {
        System.out.println(Day.MON.name());      // MON
        System.out.println(Day.MON.label());       // Monday
        System.out.println(Day.MON.ordinal());   // 0


        System.out.println(Day.valueOfLabel("Sunday"));
        System.out.println(Day.valueOfNumber(30));
    }

    private static void testOperation() {
        System.out.println(Operation1.PLUS.apply(1, 2));     // 3.0
        System.out.println(Operation1.MINUS.apply(1, 2));    // -1.0
        System.out.println(Operation1.TIMES.apply(1, 2));    // 2.0
        System.out.println(Operation1.DIVIDE.apply(1, 2));   // 0.5
    }

    private static void testFruit() {
        Fruit.APPLE.printColor();
        Fruit.ORANGE.printColor();
        Fruit.BANANA.printColor();
        Fruit.STRAWBERRY.printColor();
    }

    private static void testText() {
        Text text = new Text();
        text.applyStyles(EnumSet.of(Text.Style.BOLD, Text.Style.ITALIC));
    }
}
