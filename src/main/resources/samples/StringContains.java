public class StringContains {

    public static void main(String[] args) {
        String paragraph = "Java is a popular programming language.";

        boolean containsJava = paragraph.contains("Java");
        boolean containsHTML = paragraph.contains("HTML");
        boolean containsjavaLower = paragraph.contains("java");

        System.out.println("The paragraph is: \"" + paragraph + "\"");
        System.out.println("Does it contain \"Java\"? " + containsJava);
        System.out.println("Does it contain \"HTML\"? " + containsHTML);
        System.out.println("Does it contain \"java\"? " + containsjavaLower);
    }
}