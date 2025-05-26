public class StringTrim {

    public static void main(String[] args) {
        String messyString = "   Whitespace at the start and end!   ";
        String noWhitespaceString = "No extra space here.";

        String trimmedString = messyString.trim();
        String trimmedNoChange = noWhitespaceString.trim();

        System.out.println("Original String: \"" + messyString + "\"");
        System.out.println("Trimmed String: \"" + trimmedString + "\"");

        System.out.println("\nOriginal String: \"" + noWhitespaceString + "\"");
        System.out.println("Trimmed String: \"" + trimmedNoChange + "\"");
    }
}