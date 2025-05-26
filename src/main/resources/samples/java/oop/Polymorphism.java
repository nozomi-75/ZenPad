public class Polymorphism {
    public static void main(String[] args) {
        Dog dog = new Dog("Choco");
        dog.makeSound(5);
        Cat cat = new Cat("Alpine");
        cat.makeSound();
    }
}

class Cat extends Pet {
    Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " meows.");
    }
}

class Dog extends Pet {
    Dog (String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " barks.");
    }

    void makeSound(int volume) {
        String barkSound = getName() + " barks";
        if (volume >=5) {
            barkSound += " loudly!";
        } else {
            barkSound += " softly.";
        }
        System.out.println(barkSound);
    }
}

class Pet {
    private String name;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void makeSound() {
        System.out.println(name + " is making a sound.");
    }
}