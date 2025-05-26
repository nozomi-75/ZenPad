public class StringEquals {

    public static void main(String[] args) {

        String strA = "apple";
        String strB = "apple";
        String strC = "banana";
        String strD = new String("apple");

        boolean aEqualsB = strA.equals(strB);
        boolean aEqualsC = strA.equals(strC);
        boolean aEqualsD = strA.equals(strD);

        System.out.println("Comparing string content using .equals():");
        System.out.println("\"" + strA + "\" equals \"" + strB + "\"? " + aEqualsB);
        System.out.println("\"" + strA + "\" equals \"" + strC + "\"? " + aEqualsC);
        System.out.println("\"" + strA + "\" equals \"" + strD + "\"? " + aEqualsD);

        System.out.println("\n--- Caution: Using == ---");
        System.out.println("\"" + strA + "\" == \"" + strB + "\"? " + (strA == strB));
        System.out.println("\"" + strA + "\" == \"" + strD + "\"? " + (strA == strD));
    }
}