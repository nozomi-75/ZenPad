public class Ternary {
    public static void main(String[] args) {
        int number = 10;
        String identify = (number > 0) ? "The number is positive." : (number < 0) ? "The number is negative." : "The number is zero.";
        
        System.out.println("Number = " + number);
        System.out.println(identify);
    }
}