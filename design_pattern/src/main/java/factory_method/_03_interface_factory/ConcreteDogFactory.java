package factory_method._03_interface_factory;

import factory_method.model.Dog;
import factory_method.model.Pet;

public class ConcreteDogFactory implements PetFactory {
    @Override
    public Pet newPet() {
        System.out.println("새로운 강아지 생성");
        return new Dog();
    }
}
