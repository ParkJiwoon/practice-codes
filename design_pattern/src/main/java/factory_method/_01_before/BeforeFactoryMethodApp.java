package factory_method._01_before;

import factory_method.model.Cat;
import factory_method.model.Dog;
import factory_method.model.Pet;

public class BeforeFactoryMethodApp {
    public static void main(String[] args) {
        Pet cat = new Cat();
        Pet dog = new Dog();

        cat.walk();
        dog.walk();
    }
}
