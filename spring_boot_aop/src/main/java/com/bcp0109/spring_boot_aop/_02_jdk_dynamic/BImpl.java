package com.bcp0109.spring_boot_aop._02_jdk_dynamic;

public class BImpl implements BInterface {

    @Override
    public String call() {
        System.out.println("B 호출");
        return "b";
    }
}
