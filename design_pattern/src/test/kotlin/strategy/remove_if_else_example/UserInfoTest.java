package strategy.remove_if_else_example;

import _04_strategy.remove_if_else_example.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import _04_strategy.remove_if_else_example.filter.BetterFilter;
import _04_strategy.remove_if_else_example.specification.AgeSpec;
import _04_strategy.remove_if_else_example.specification.AndSpec;
import _04_strategy.remove_if_else_example.specification.NameSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserInfoTest {

    @Test
    @DisplayName("if else 로 필터링")
    void testIfElse() {
        List<UserInfo> userInfos = generateUserList();

        // 1. 이름이 이순신인거 찾기
        List<UserInfo> nameItems = new ArrayList<>();
        for (UserInfo userInfo : userInfos) {
            if (userInfo.getName().equals("이순신")) {
                nameItems.add(userInfo);
            }
        }
        assertThat(nameItems.size()).isEqualTo(2);
        nameItems.forEach(nameItem -> assertThat(nameItem.getName()).isEqualTo("이순신"));


        // 2. 나이가 23 인 UserInfo 찾기
        List<UserInfo> ageItems = new ArrayList<>();
        for (UserInfo userInfo : userInfos) {
            if (userInfo.getAge() == 23) {
                ageItems.add(userInfo);
            }
        }
        assertThat(ageItems.size()).isEqualTo(2);
        ageItems.forEach(ageItem -> assertThat(ageItem.getAge()).isEqualTo(23));


        // 3. 이름이 이순신이고 나이가 23 인 경우 찾기
        List<UserInfo> nameAndAgeItems = new ArrayList<>();
        for (UserInfo userInfo : userInfos) {
            if (userInfo.getName().equals("이순신") && userInfo.getAge() == 23) {
                nameAndAgeItems.add(userInfo);
            }
        }
        assertThat(nameAndAgeItems.size()).isEqualTo(1);
        nameAndAgeItems.forEach(nameAndAgeItem -> {
            assertThat(nameAndAgeItem.getName()).isEqualTo("이순신");
            assertThat(nameAndAgeItem.getAge()).isEqualTo(23);
        });
    }

    @Test
    @DisplayName("전략 패턴 적용해서 필터링")
    void testStrategyPattern() {
        List<UserInfo> userInfos = generateUserList();

        BetterFilter<UserInfo> betterFilter = new BetterFilter<>();

        // 1. 이름이 이순신인거 찾기
        NameSpec nameSpec = new NameSpec("이순신");
        List<UserInfo> nameItems = betterFilter.filter(userInfos, nameSpec);
        assertThat(nameItems.size()).isEqualTo(2);
        nameItems.forEach(nameItem -> assertThat(nameItem.getName()).isEqualTo("이순신"));


        // 2. 나이가 23 인 strategy.remove_if_else_example.UserInfo 찾기
        AgeSpec ageSpec = new AgeSpec(23);
        List<UserInfo> ageItems = betterFilter.filter(userInfos, ageSpec);
        assertThat(ageItems.size()).isEqualTo(2);
        ageItems.forEach(ageItem -> assertThat(ageItem.getAge()).isEqualTo(23));


        // 3. 이름이 이순신이고 나이가 23 인 경우 찾기
        AndSpec<UserInfo> nameAndAgeSpec = new AndSpec<>(nameSpec, ageSpec);
        List<UserInfo> nameAndAgeItems = betterFilter.filter(userInfos, nameAndAgeSpec);
        assertThat(nameAndAgeItems.size()).isEqualTo(1);
        nameAndAgeItems.forEach(nameAndAgeItem -> {
            assertThat(nameAndAgeItem.getName()).isEqualTo("이순신");
            assertThat(nameAndAgeItem.getAge()).isEqualTo(23);
        });
    }

    private static List<UserInfo> generateUserList() {
        return Arrays.asList(
                new UserInfo(1, "홍길동", 25),
                new UserInfo(2, "이순신", 23),
                new UserInfo(3, "안중근", 20),
                new UserInfo(4, "김구", 30),
                new UserInfo(6, "이순신", 25),
                new UserInfo(7, "안중근", 23)
        );
    }
}
