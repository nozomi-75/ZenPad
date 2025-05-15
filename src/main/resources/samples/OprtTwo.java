public class OprtTwo {
    public static void main(String[] args) {
        
        // Declare variables
        int a = 8;
        int b = 5;
        String divider = "--------------------";
        
        // a*b with arithmetic operator
        int product = a * b;
        
        // Conditional operator with relational and logical operators in the condition
        String result = ((product % 2) == 0) ? "The product is even." : "The product is odd.";
        
        // Print stuff
        System.out.println("a = " + a + ", " + "b = " + b);
        System.out.println("a × b = " + product);
        System.out.println(divider);
        System.out.println(result);
    }
}