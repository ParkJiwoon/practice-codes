package _01_factory_method.factory_method.factory;

import _01_factory_method.factory_method.model.User;

public abstract class UserFactory {

    public User newInstance() {
        User user = createUser();
        user.signup();
        return user;
    }

    protected abstract User createUser();
}
