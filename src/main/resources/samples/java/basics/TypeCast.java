public class TypeCast {
    public static void main(String[] args) {
        int myInt = 42;
        double myDouble = myInt;

        System.out.println("Widening casting:");
        System.out.println("int value: " + myInt);
        System.out.println("double value: " + myDouble);

        double price = 19.99;
        int roundedPrice = (int) price;

        System.out.println("\nNarrowing casting:");
        System.out.println("double value: " + price);
        System.out.println("int value after casting: " + roundedPrice);
    }
}