package strategy.remove_if_else_example.specification;

import strategy.remove_if_else_example.UserInfo;

public class NameSpec implements Spec<UserInfo> {

    private final String name;

    public NameSpec(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfied(UserInfo item) {
        return name.equals(item.getName());
    }
}
