import java.util.Scanner;

public class ScannerNumbers {
    public static void main(String[] args) {
        int[] numInput = new int[5];
        Scanner input = new Scanner(System.in);
        
        for (int i = 0; i < numInput.length; i++) {
        	numInput[i] = getInput("Type a number (" + (i + 1) + "/" + numInput.length + "): ", input);
        }
        
        int sum = 0;
        for (int number : numInput) {
            sum += number;
        }
        
        System.out.println("The sum of the numbers is: " + sum);
        input.close();
    }

    static int getInput(String prompt, Scanner input) {
    	int number;

    	while (true) {
    		System.out.print(prompt);
    		if (input.hasNextInt()) {
    			number = input.nextInt();
    			break;
    		} else {
    			System.out.println("Invalid input. Please enter an integer.");
    			input.next();
    		}
    	}

    	return number;
    }
}