package _01_factory_method.factory_method;

import _01_factory_method.factory_method.factory.DeveloperFactory;
import _01_factory_method.factory_method.factory.KakaoDeveloperFactory;
import _01_factory_method.factory_method.factory.NaverDeveloperFactory;
import _01_factory_method.factory_method.model.Developer;

public class FactoryMethodApp {
    public static void main(String[] args) {
        getDeveloper(new KakaoDeveloperFactory());
        getDeveloper(new NaverDeveloperFactory());
    }

    private static void getDeveloper(DeveloperFactory developerFactory) {
        Developer developer = developerFactory.newInstance();
    }
}
