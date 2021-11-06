package com.example.springjpa;

import com.example.springjpa.n_plus_1.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class NPlus1Test {

    @Autowired
    private NPlus1Service service;

    @BeforeEach
    void setUp() {
        service.setUp();
    }

    @Test
    void testCallGetter() {
        System.out.println("---------------- testCallGetter start --------------");
        Long lastPostId = 5L;
        service.getFeedsByGetter(lastPostId);
        System.out.println("---------------- testCallGetter end --------------");
    }

    @Test
    void testFetchJoin() {
        System.out.println("---------------- testFetchJoin start --------------");
        Long lastPostId = 5L;
        service.getFeedsByFetchJoin(lastPostId);
        System.out.println("---------------- testFetchJoin end --------------");
    }

    @Test
    void testInQuery() {
        System.out.println("---------------- testInQuery start --------------");
        Long lastPostId = 5L;
        service.getFeedsByIn(lastPostId);
        System.out.println("---------------- testInQuery end --------------");
    }

    @Test
    void testJoin() {
        System.out.println("---------------- testJoin start --------------");
        Long lastPostId = 5L;
        service.getFeedsByJoin(lastPostId);
        System.out.println("---------------- testJoin end --------------");
    }
}
