import java.util.Scanner;

public class ThrowingExceptions {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int age = 0;

		while (true) {
			System.out.print("Type your age here: ");
			if (input.hasNextInt()) {
				age = input.nextInt();
				break;
			} else {
				System.out.println("Please enter a valid age.");
				input.nextLine();
			}
		}

		if (age < 18) {
            input.close();
			throw new IllegalArgumentException("You must be 18 or older.");
		}

		System.out.println("You're free to go.");
		input.close();
	}
}