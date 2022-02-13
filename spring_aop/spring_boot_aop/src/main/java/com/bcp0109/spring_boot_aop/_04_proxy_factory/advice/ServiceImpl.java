package com.bcp0109.spring_boot_aop._04_proxy_factory.advice;

public class ServiceImpl implements ServiceInterface {

    @Override
    public void call() {
        System.out.println("ServiceImpl 호출");
    }
}
