package com.practice.springtest.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StringTest {

    @DisplayName("containsExactly: 순서를 포함해서 배열이 정확히 일치해야 함")
    @Test
    void containsExactlyTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        assertThat(list).containsExactly(1, 2, 3);

        /*
         * 실패 케이스
         *
         * assertThat(list).containsExactly(1, 2);       -> 원소 3 이 일치하지 않아서
         * assertThat(list).containsExactly(3, 2, 1);    -> list 의 순서가 달라서 실패
         * assertThat(list).containsExactly(1, 2, 3, 3); -> list 에 중복된 원소가 있어서 실패
         */
    }

    @DisplayName("containsOnly: 원소의 순서, 중복 여부 무시하고 값만 일치하면 됨")
    @Test
    void containsOnlyTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        assertThat(list).containsOnly(1, 2, 3);
        assertThat(list).containsOnly(3, 2, 1);     // 순서가 다르지만 성공
        assertThat(list).containsOnly(1, 2, 3, 3);  // 중복이지만 성공

        /*
         * 실패 케이스
         *
         * assertThat(list).containsOnly(1, 2);       -> 원소 3 이 일치하지 않아서 실패
         * assertThat(list).containsOnly(1, 2, 3, 4); -> 원소 4 가 일치하지 않아서 실패
         */
    }

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
