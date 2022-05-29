package _01_factory_method.factory_method.factory;

import _01_factory_method.factory_method.model.User;
import _01_factory_method.factory_method.model.NaverUser;

public class NaverUserFactory extends UserFactory {
    @Override
    protected User createUser() {
        return new NaverUser();
    }
}
