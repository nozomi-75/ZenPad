public class MethodWithParameters {
    public static void greet(String name) {
        System.out.println("Welcome to Java Programming, " + name + "!");
    }

    public static void printSum(int num1, int num2) {
        int sum = num1 + num2;
        System.out.println("The sum of " + num1 + " and " + num2 + " is " + sum + ".");
    }

    public static void main(String[] args) {
        greet("Keiaa");
        printSum(50, 25);
    }
}