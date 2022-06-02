package _04_strategy.remove_if_else_example.specification;

import _04_strategy.remove_if_else_example.UserInfo;

public class AgeSpec implements Spec<UserInfo> {

    private final int age;

    public AgeSpec(int age) {
        this.age = age;
    }

    @Override
    public boolean isSatisfied(UserInfo item) {
        return age == item.getAge();
    }
}
