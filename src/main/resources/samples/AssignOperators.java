public class AssignOperators {
    public static void main(String[] args) {
        int number = 10; // Simple assignment
        System.out.println("Initial value: " + number);

        number += 5; // Add and assign
        System.out.println("After += 5: " + number);

        number -= 3; // Subtract and assign
        System.out.println("After -= 3: " + number);

        number *= 2; // Multiply and assign
        System.out.println("After *= 2: " + number);

        number /= 4; // Divide and assign
        System.out.println("After /= 4: " + number);

        number %= 3; // Modulus and assign
        System.out.println("After %= 3: " + number);
    }
}