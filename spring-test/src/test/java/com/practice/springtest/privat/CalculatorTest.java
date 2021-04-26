package com.practice.springtest.privat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    @DisplayName("Private Method 테스트 : 파라미터 존재")
    @Test
    void addTest() throws Exception {
        Calculator calculator = new Calculator("");

        // private method 를 가져옴
        Method method = calculator.getClass().getDeclaredMethod("add", int.class, int.class);
        method.setAccessible(true);

        // add 메소드 실행
        int result = (int) method.invoke(calculator, 1, 2);
        assertThat(result).isEqualTo(3);
    }

    @DisplayName("Private Method 테스트 : 파라미터 없음")
    @Test
    void printTest() throws Exception {
        Calculator calculator = new Calculator("");

        // private method 를 가져옴
        Method method = calculator.getClass().getDeclaredMethod("print");
        method.setAccessible(true);

        // print 메소드 실행
        method.invoke(calculator);
    }

    @DisplayName("Private Field 테스트")
    @Test
    void ownerTest() throws Exception {
        Calculator calculator = new Calculator("My Name");

        // private field 를 가져옴
        Field field = calculator.getClass().getDeclaredField("owner");
        field.setAccessible(true);

        // owner 값 꺼내기
        String owner = (String) field.get(calculator);
        assertThat(owner).isEqualTo("My Name");
    }
}