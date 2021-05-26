package com.practice.notepad;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BitTest {

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7})
    void testBitTrue(int input) {
        Assertions.assertThat((input & 4) != 0).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 8})
    void testBitFalse(int input) {
        Assertions.assertThat((input & 4) != 0).isFalse();
    }
}
