package com.bcp0109.spring_boot_aop._02_jdk_dynamic;

public class AImpl implements AInterface {

    @Override
    public String call() {
        System.out.println("A 호출");
        return "a";
    }
}
