package com.practice.springtest.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class ContainsTest {

    @DisplayName("contains: 원소값 포함하는지 여부 확인")
    @Test
    void containsTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        // Success: 모든 원소를 입력하지 않아도 성공
        assertThat(list).contains(1, 2);

        // Success: 중복된 값이 있어도 포함만 되어 있으면 성공
        assertThat(list).contains(1, 2, 2);

        // Success: 순서가 바뀌어도 값만 맞으면 성공
        assertThat(list).contains(3, 2);

        // Fail: List 에 없는 값을 입력하면 실패
        assertThat(list).contains(1, 2, 3, 4);
    }

    @DisplayName("String 을 contains 로 테스트")
    @Test
    void stringContainsTest() {
        String str = "abc";

        assertThat(str).contains("a", "b", "c");
    }

    @DisplayName("Array 를 contains 로 테스트")
    @Test
    void arrayContainsTest() {
        int[] arr = {1, 2, 3, 4};

        assertThat(arr).contains(1, 2, 3, 4);
    }

    @DisplayName("Set 을 contains 로 테스트")
    @Test
    void setContainsTest() {
        Set<Integer> set = Set.of(1, 2, 3);

        assertThat(set).contains(1, 2, 3);
    }

    /*
     * containsOnly 실패 케이스
     *
     * assertThat(list).containsOnly(1, 2);       -> 원소 3 이 일치하지 않아서 실패
     * assertThat(list).containsOnly(1, 2, 3, 4); -> 원소 4 가 일치하지 않아서 실패
     */
    @DisplayName("containsOnly: 순서, 중복을 무시하는 대신 원소값과 갯수가 정확히 일치")
    @Test
    void containsOnlyTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        assertThat(list).containsOnly(1, 2, 3);
        assertThat(list).containsOnly(3, 2, 1);
        assertThat(list).containsOnly(1, 2, 3, 3);
    }

    /*
     * containsExactly 실패 케이스
     *
     * assertThat(list).containsExactly(1, 2);       -> 원소 3 이 일치하지 않아서
     * assertThat(list).containsExactly(3, 2, 1);    -> list 의 순서가 달라서 실패
     * assertThat(list).containsExactly(1, 2, 3, 3); -> list 에 중복된 원소가 있어서 실패
     */
    @DisplayName("containsExactly: 순서를 포함해서 정확히 일치해야 함")
    @Test
    void containsExactlyTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        assertThat(list).containsExactly(1, 2, 3);
    }
}
