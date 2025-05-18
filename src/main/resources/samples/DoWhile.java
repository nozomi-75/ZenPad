// Import the Scanner class to read user input
import java.util.Scanner;

public class DoWhile {
    public static void main(String[] args) {
        // Create a new Scanner object to read user input from terminal
        Scanner scanner = new Scanner(System.in);
        // Declare an integer variable to store the user's input
        int input;
        
        do {
            System.out.println("Enter 0 to exit the program...");
            // Read the user's input and store it in the 'input' variable
            input = scanner.nextInt();
        } while (input != 0);
        
        System.out.println("You have exited the program.");
        // Close the Scanner object to prevent resource leaks
        scanner.close();
    }
}