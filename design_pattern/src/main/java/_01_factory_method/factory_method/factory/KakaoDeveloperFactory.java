package _01_factory_method.factory_method.factory;

import _01_factory_method.factory_method.model.Developer;
import _01_factory_method.factory_method.model.KakaoDeveloper;

public class KakaoDeveloperFactory extends DeveloperFactory {
    @Override
    protected Developer createDeveloper() {
        return new KakaoDeveloper();
    }
}
