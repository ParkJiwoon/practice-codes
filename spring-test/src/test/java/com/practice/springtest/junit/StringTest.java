package com.practice.springtest.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringTest {

    @DisplayName("'1,2' 를 , 로 split 했을 때 1 과 2 로 잘 분리되는 지 확인")
    @Test
    void splitTest() {
        // given
        String str = "1,2";

        // when
        String[] split = str.split(",");

        // then
        assertThat(split).contains("1", "2");
    }

    @DisplayName("'1' 을 , 로 split 했을 때 1 만 포함하는 배열이 반환되는지 확인")
    @Test
    void splitOnlyOneTest() {
        // given
        String str = "1";

        // when
        String[] split = str.split(",");

        // then
        assertThat(split).containsExactly("1");
    }

    @DisplayName("'(1,2)' 값이 주어졌을 때 substirng() 메소드로 괄호를 제거하고 '1,2' 를 반환")
    @Test
    void substringTest() {
        // given
        String str = "(1,2)";

        // when
        // substring 은 beginIndex 는 포함하고 endIndex 는 포함하지 않음
        String substring = str.substring(1, str.length() - 1);

        // then
        assertThat(substring).isEqualTo("1,2");
    }

    @DisplayName("'abc' 값이 주어졌을 때 charAt() 메소드로 범위 벗어나면 StringIndexOutOfBoundsException 발생")
    @Test
    void charAtTest() {
        // given
        String str = "abc";

        // when
        assertThatThrownBy(() -> str.charAt(str.length()))
                .isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("String index out of range: 3");

        assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
                .isThrownBy(() -> str.charAt(str.length()))
                .withMessageMatching("String index out of range: 3");
    }
}
