package _01_factory_method.factory_method;

import _01_factory_method.factory_method.factory.UserFactory;
import _01_factory_method.factory_method.factory.KakaoUserFactory;
import _01_factory_method.factory_method.factory.NaverUserFactory;
import _01_factory_method.factory_method.model.User;

public class FactoryMethodApp {
    public static void main(String[] args) {
        showUser(new KakaoUserFactory());
        showUser(new NaverUserFactory());
    }

    private static void showUser(UserFactory userFactory) {
        User user = userFactory.newInstance();
    }
}
