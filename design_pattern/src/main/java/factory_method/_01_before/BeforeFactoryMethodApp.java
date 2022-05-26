package factory_method._01_before;

import factory_method._02_simple_factory.model.Cat;
import factory_method._02_simple_factory.model.Dog;
import factory_method._02_simple_factory.model.Pet;

public class BeforeFactoryMethodApp {
    public static void main(String[] args) {
        Pet cat = new Cat();
        Pet dog = new Dog();

        cat.walk();
        dog.walk();
    }
}
