package factoryMethod;

public class PetFactory {

    public Pet createPet(PetType type) {
        switch (type) {
            case DOG:
                return new Dog();
            case CAT:
                return new Cat();
            default:
                throw new IllegalArgumentException("지원하지 않는 Pet 타입입니다.");
        }
    }
}
