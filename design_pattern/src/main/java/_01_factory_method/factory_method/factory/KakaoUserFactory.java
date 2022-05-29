package _01_factory_method.factory_method.factory;

import _01_factory_method.factory_method.model.User;
import _01_factory_method.factory_method.model.KakaoUser;

public class KakaoUserFactory extends UserFactory {
    @Override
    protected User createUser() {
        return new KakaoUser();
    }
}
