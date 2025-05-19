import java.util.Scanner;

public class TryCatch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Dividend: ");
        int dividend = input.nextInt();
        System.out.print("Divisor: ");
        int divisor = input.nextInt();

        try {
            int result = dividend / divisor;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            input.close();
            System.out.println("Error: Cannot divide by zero.");
        }
    }
}