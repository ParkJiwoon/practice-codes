package com.example.springwebfluxmongodb.loader;

import com.example.springwebfluxmongodb.entity.Item;
import com.example.springwebfluxmongodb.repository.BlockingItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * CommandLineRunner 는 애플리케이션이 시작된 후에 자동으로 실행되는 특수한 스프링 부트 컴포넌트
 * run() 메소드 하나만 갖고 있는 함수형 인터페이스
 * 애플리케이션에 사용되는 모든 컴포넌트가 등록되고 활성화된 이후에 run() 메소드가 자동으로 실행되는 것이 보장됨
 * 특정 순서로 동족하는 것이 보장되지 않기 때문에 여러 개의 CommandLineRunner 객체를 조율해서 순서에 맞게 실행시키게 짜면 안됨
 */

@Component
public class RepositoryDatabaseLoader {

    @Bean
    CommandLineRunner initialize(BlockingItemRepository repository) {
        return args -> {
            repository.save(new Item("Alf alarm clock", 19.99));
            repository.save(new Item("Smurf TV tray", 24.99));
        };
    }
}
