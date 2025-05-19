public class Inheritance {
    public static void main(String[] args) {
        Dog dog = new Dog("Choco", 3);
        dog.eat();
        dog.sleep();
        Cat cat = new Cat("Alpine", 2);
        cat.eat();
        cat.sleep();
    }
}

class Cat extends Pet {
    Cat(String name, int age) {
        super(name, age);
    }
}

class Dog extends Pet {
    Dog (String name, int age) {
        super(name, age);
    }
}

class Pet {
    private String name;
    private int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Name\t: " + name);
        System.out.println("Age \t: " + age);
        System.out.println();
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
}