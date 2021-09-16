package com.jiwoon;

public class Main {

    public static void main(String[] args) {
        System.out.println(Day.MON.name());      // MON
        System.out.println(Day.MON.label());       // Monday
        System.out.println(Day.MON.ordinal());   // 0


        System.out.println(Day.valueOfLabel("Sunday"));
        System.out.println(Day.valueOfNumber(30));
    }
}
