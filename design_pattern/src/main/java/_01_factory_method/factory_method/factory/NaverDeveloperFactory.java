package _01_factory_method.factory_method.factory;

import _01_factory_method.factory_method.model.Developer;
import _01_factory_method.factory_method.model.NaverDeveloper;

public class NaverDeveloperFactory extends DeveloperFactory {
    @Override
    protected Developer createDeveloper() {
        return new NaverDeveloper();
    }
}
