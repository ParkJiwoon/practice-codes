package com.bcp0109.spring_boot_aop._02_jdk_dynamic;

public class SampleImpl implements SampleInterface {

    @Override
    public void foo() {
        System.out.println("Foo 메서드 호출");
    }
}
