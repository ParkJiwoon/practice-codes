package com.example.springredis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.Objects;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RedisSyncTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    @DisplayName("두 쓰레드가 각각 값을 가져와서 업데이트 하는 동시성 이슈 재현")
    void testSyncIssue() {
        // setup
        String key = "syncStringKey";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, "1");

        // given
        ValueOperations<String, String> thread1 = redisTemplate.opsForValue();
        ValueOperations<String, String> thread2 = redisTemplate.opsForValue();

        // when
        // 1. 두 개의 쓰레드가 동시에 값을 꺼냄
        String t1 = thread1.get(key);
        String t2 = thread2.get(key);
        assertThat(t1).isEqualTo("1");
        assertThat(t2).isEqualTo("1");

        // 2. 각각 업데이트
        thread1.set(key, t1 + 1);
        thread2.set(key, t2 + 1);

        // then
        String value1 = thread1.get(key);
        String value2 = thread2.get(key);
        assertThat(value1).isEqualTo("11");
        assertThat(value2).isEqualTo("11");

        // teardown
        redisTemplate.delete(key);
    }

    @Test
    @DisplayName("incr 로 횟수 체크해서 방지: 동시 실행만 방지할 수 있고 1, 2, 3 처럼 카운팅 한계가 있음")
    void testIncr() {
        // setup
        String key = "testIncrKey";
        String countKey = "testIncrCountKey";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, "1");

        // given
        ValueOperations<String, String> thread1 = redisTemplate.opsForValue();
        ValueOperations<String, String> thread2 = redisTemplate.opsForValue();

        // when
        // 1. 두 개의 쓰레드가 동시에 값을 꺼냄
        String t1 = thread1.get(key);
        String t2 = thread2.get(key);
        assertThat(t1).isEqualTo("1");
        assertThat(t2).isEqualTo("1");

        // 2. 각각 업데이트할 때 incr 로 횟수 업데이트 1이면 이미 업데이트 된거임
        if (Objects.equals(thread1.increment(countKey), 1L)) {
            thread1.set(key, t1 + 1);
        }

        if (Objects.equals(thread2.increment(countKey), 1L)) {
            throw new RuntimeException("이미 증가했기 때문에 들어오면 안됨. 즉, 한번만 실행되어야함  ");
        }

        // then
        String value1 = thread1.get(key);
        String value2 = thread2.get(key);
        assertThat(value1).isEqualTo("11");
        assertThat(value2).isEqualTo("11");

        // teardown
        redisTemplate.delete(key);
        redisTemplate.delete(countKey);
    }
}
