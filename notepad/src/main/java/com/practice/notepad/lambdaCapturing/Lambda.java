package com.practice.notepad.lambdaCapturing;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class Lambda {
    private static int classVariable;
    private int instanceVariable;

    public void localMethod() {
        int localVariable = 1357;

        Runnable r1 = () -> log.info("클래스 변수 접근 가능 {}", classVariable);
        Runnable r2 = () -> log.info("인스턴스 변수 접근 가능 {}", instanceVariable);
        Runnable r3 = () -> log.info("로컬 변수 접근 불가능 {}", localVariable);

        log.info("메인 쓰레드");

        CompletableFuture.runAsync(r1).join();
        CompletableFuture.runAsync(r2).join();

        classVariable = 1;
        instanceVariable = 2;
        // localVariable = 3; 로컬 변수를 재할당 하면 effectively final 이 아니라서 에러 발생

        CompletableFuture.runAsync(r1).join();
        CompletableFuture.runAsync(r2).join();
    }
}
