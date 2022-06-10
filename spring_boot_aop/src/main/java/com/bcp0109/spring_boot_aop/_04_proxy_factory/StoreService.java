package com.bcp0109.spring_boot_aop._04_proxy_factory;

public class StoreService {

    public void find() {
        System.out.println("StoreService - find 호출");
    }

    public void save() {
        System.out.println("StoreService - save 호출");
    }
}
