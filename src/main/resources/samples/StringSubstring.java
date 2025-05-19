public class StringSubstring {

    public static void main(String[] args) {

        String greeting = "Good Morning, World!";

        String sub1 = greeting.substring(5);
        String sub2 = greeting.substring(0, 4);
        String sub3 = greeting.substring(5, 12);

        System.out.println("Original String:\t\"" + greeting + "\"");
        System.out.println("substring(5 only):\t\"" + sub1 + "\"");
        System.out.println("substring(0, 4):\t\"" + sub2 + "\"");
        System.out.println("substring(5, 12):\t\"" + sub3 + "\"");
    }
}