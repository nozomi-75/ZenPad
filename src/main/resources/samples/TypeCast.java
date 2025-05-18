public class TypeCast {
    public static void main(String[] args) {
        // Widening casting (automatic): int to double
        int myInt = 42;
        double myDouble = myInt; // int is automatically converted to double

        System.out.println("Widening casting:");
        System.out.println("int value: " + myInt);
        System.out.println("double value: " + myDouble);

        // Narrowing casting (manual): double to int
        double price = 19.99;
        int roundedPrice = (int) price; // double is manually cast to int

        System.out.println("\nNarrowing casting:");
        System.out.println("double value: " + price);
        System.out.println("int value after casting: " + roundedPrice);
    }
}