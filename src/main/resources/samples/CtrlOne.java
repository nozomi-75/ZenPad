public class CtrlOne {
    public static void main(String[] args) {
        
        // Declare and print variable
        int number = 10;
        System.out.println("Number = " + number);
        
        // else if statement
        if (number > 0) {
            System.out.println("The number is positive.");
        } else if (number < 0) {
            System.out.println("The number is negative.");
        } else {
            System.out.println("The number is zero.");
        }
    }
}