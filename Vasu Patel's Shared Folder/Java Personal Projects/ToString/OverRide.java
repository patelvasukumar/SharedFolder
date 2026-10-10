package ToString;

class Animal {
    public void makeSound() {
        System.out.println("Some Generic Animal Sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark");
    }
}

public class OverRide {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.makeSound();
    }
}
