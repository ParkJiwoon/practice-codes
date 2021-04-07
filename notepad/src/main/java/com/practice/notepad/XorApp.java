package com.practice.notepad;

public class XorApp {
    public static void main(String[] args) {
        // 하나라도 null 이 아니면 false
        System.out.println(invalid(null, "email"));
        System.out.println(invalid("display", null));

        // 해당되지 않으면 true
        System.out.println(invalid(null, null));
        System.out.println(invalid("display", "email"));
    }

    public static boolean invalid(String displayId, String email) {
        return displayId != null ^ email == null;
    }
}
