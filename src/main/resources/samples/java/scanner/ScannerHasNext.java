import java.util.Scanner;

public class ScannerHasNext {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter some words. Type 'quit' to finish.");
        while (input.hasNext()) { 
            String word = input.next();
            if (word.equalsIgnoreCase("quit")) {
                break;
            }
            System.out.println("You entered: " + word);
        }
        System.out.println("Finished reading input.");
        input.close();
    }
}