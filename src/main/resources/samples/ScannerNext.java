import java.util.Scanner;

public class ScannerNext {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your first name and last name separated by a space: ");
        String firstName = input.next(); // Reads the first word
        String lastName = input.next();  // Reads the next word
        System.out.println("Your first name is: " + firstName);
        System.out.println("Your last name is: " + lastName);
        input.close();
    }
}