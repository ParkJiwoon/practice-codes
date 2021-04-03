package com.jiwoon.practicewebmvc.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 가짜 네트워크 클라이언트
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        System.out.println("setUrl");
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("disconnect: " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("close");
        disconnect();
    }

}
