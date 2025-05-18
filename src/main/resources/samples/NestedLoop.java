public class NestedLoop {
    public static void main(String[] args) {
        // This outer loop will iterate twice, with the variable 'i' taking values 1 and 2.
        for (int i = 1; i <= 2; i++) {
            System.out.println("Outer: " + i);
            
            // This inner loop will iterate three times for each iteration of the outer loop.
            for (int j = 1; j <= 3; j++) {
                System.out.println("Inner: " + j);
            }
        }
    }
}