package com.bcp0109.springwebfluxkotlin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MyApplication {

    private static Logger log = LoggerFactory.getLogger(MyApplication.class);

    @GetMapping("/mine")
    public ResponseEntity<String> mine() {
        log.info("request");

        try {
            Thread.sleep(5000);
        } catch (Exception ignored) {
        }

        return ResponseEntity.ok().body("mine");
    }

    public static void main(String[] args) {
        System.setProperty("server.port", "8181");
        SpringApplication.run(MyApplication.class, args);
    }
}
