public class StringIsEmpty {

    public static void main(String[] args) {
        String nonEmptyString = "I have characters!";
        String emptyString = "";
        String whitespaceString = "   ";

        boolean isNonEmptyEmpty = nonEmptyString.isEmpty();
        boolean isStringEmpty = emptyString.isEmpty();
        boolean isWhitespaceEmpty = whitespaceString.isEmpty();

        System.out.println("Is \"" + nonEmptyString + "\" empty? " + isNonEmptyEmpty);
        System.out.println("Is \"" + emptyString + "\" empty? " + isStringEmpty);
        System.out.println("Is \"" + whitespaceString + "\" empty? " + isWhitespaceEmpty);
    }
}