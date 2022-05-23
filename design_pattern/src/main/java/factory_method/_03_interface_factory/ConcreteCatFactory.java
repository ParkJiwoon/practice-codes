package factory_method._03_interface_factory;

import factory_method.model.Cat;
import factory_method.model.Pet;

public class ConcreteCatFactory implements PetFactory {
    @Override
    public Pet newPet() {
        System.out.println("새로운 고양이 생성");
        return new Cat();
    }
}
