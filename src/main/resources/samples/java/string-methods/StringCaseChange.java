public class StringCaseChange {
    public static void main(String[] args) {
        String str = "Java Programming is Fun!";
        String upperCaseStr = str.toUpperCase();

        System.out.println("Original String: " + str);
        System.out.println("Uppercase String: " + upperCaseStr);
        String lowerCaseStr = str.toLowerCase();
        System.out.println("Lowercase String: " + lowerCaseStr);
    }
}