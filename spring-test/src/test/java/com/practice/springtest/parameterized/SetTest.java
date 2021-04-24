package com.practice.springtest.parameterized;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @DisplayName("Set 사이즈 테스트")
    @Test
    void sizeTest() {
        assertThat(numbers.size()).isEqualTo(3);
    }

    @DisplayName("값 존재를 테스트 한다 (중복 코드 존재)")
    @Test
    void containsTest() {
        assertThat(numbers.contains(1)).isTrue();
        assertThat(numbers.contains(2)).isTrue();
        assertThat(numbers.contains(3)).isTrue();
    }

    @DisplayName("ParameterizedTest 를 활용해서 중복 코드 제거")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void containsParameterizedTest(int input) {
        assertThat(numbers.contains(input)).isTrue();
    }

    @DisplayName("CsvSource 를 활용해서 여러 input 과 expected 에 대해서 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1,true", "2,true", "3,true", "4,false", "5,false"})
    void containsTrueAndFalseTest(String input, String expected) {
        int actual = Integer.parseInt(input);
        boolean expect = Boolean.parseBoolean(expected);

        assertThat(numbers.contains(actual)).isEqualTo(expect);
    }
}
