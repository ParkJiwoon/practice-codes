package com.example.springwebfluxmongodb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * build.gradle 에서 blockhound-junit-platform 을 활성화 한 후에 실행하면 정상 동작하는데
 * 저 라이브러리를 활성화하면 다른 테스트 코드에서 Thymeleaf 블로킹 코드에 걸려서 비활성화 해둔다
 */
public class BlockHoundUnitTest {

    @DisplayName("Blocking 코드 때문에 실패하는 테스트")
    // @Test
    void threadSleepIsABlockingCall() {
        Mono.delay(Duration.ofSeconds(1))   // Mono.delay() 로 전체 플로우를 리액터 스레드에서 실행
                .flatMap(tick -> {
                    try {
                        Thread.sleep(10);   // 블록 하운드가 검출하려는 블록킹 호출
                        return Mono.just(true);
                    } catch (InterruptedException e) {
                        return Mono.error(e);
                    }
                })
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @DisplayName("Blocking 코드 에러를 잡는 테스트 코드")
    // @Test
    void threadSleepIsABlockingCallIsFail() {
        Mono.delay(Duration.ofSeconds(1))   // Mono.delay() 로 전체 플로우를 리액터 스레드에서 실행
                .flatMap(tick -> {
                    try {
                        Thread.sleep(10);   // 블록 하운드가 검출하려는 블록킹 호출
                        return Mono.just(true);
                    } catch (InterruptedException e) {
                        return Mono.error(e);
                    }
                })
                .as(StepVerifier::create)
                .verifyErrorMatches(throwable -> {
                    Assertions.assertThat(throwable.getMessage()).contains("Blocking call! java.lang.Thread.sleep");
                    return true;
                });
    }
}
