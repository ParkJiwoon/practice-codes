package _01_factory_method.factory_method.factory;

import _01_factory_method.factory_method.model.Developer;

public abstract class DeveloperFactory {

    public Developer newInstance() {
        Developer developer = createDeveloper();
        developer.registerEmail();
        developer.allowAccess();
        return developer;
    }

    protected abstract Developer createDeveloper();
}
