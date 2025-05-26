public class Encapsulation {
    public static void main(String[] args) {
        Pet dog = new Pet("Choco", 3);
        Pet cat = new Pet("Alpine", 2);
        dog.displayInfo();
        cat.displayInfo();
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
}