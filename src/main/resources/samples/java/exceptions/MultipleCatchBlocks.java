import java.util.Scanner;

public class MultipleCatchBlocks {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] numbers = {10, 20, 30};

        System.out.print("Enter an index (0-2): ");
        try {
            int index = input.nextInt();
            int result = 100 / numbers[index];
            System.out.println("100 divided by numbers[" + index + "] is " + result);
        } catch (ArithmeticException e) {
            System.out.println("You tried to divide by zero.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("That index is out of range.");
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        input.close();
    }
}