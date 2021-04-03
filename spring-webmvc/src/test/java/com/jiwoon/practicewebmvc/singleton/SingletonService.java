package com.jiwoon.practicewebmvc.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();

    // 2. 생성자 대신 외부에 인스턴스를 제공하는 메서드를 만듬
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 외부에서 임의로 생성하지 못하게 생성자를 private 으로 변경
    private SingletonService() { }


    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
