public class Ternary {
    public static void main(String[] args) {
        int number = 10;
        String identify = (number > 0) ? "The number is positive." : "The number is negative or zero.";
        
        System.out.println("Number = " + number);
        System.out.println(identify);
    }
}