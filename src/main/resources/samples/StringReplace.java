public class StringReplace {

    public static void main(String[] args) {
        String original = "The quick brown fox jumps over the lazy dog.";
        String replacedFox = original.replace("fox", "cat");
        String replacedO = original.replace('o', '*');
        System.out.println("Original String: \"" + original + "\"");
        System.out.println("After replacing 'fox': \"" + replacedFox + "\"");
        System.out.println("After replacing 'o':   \"" + replacedO + "\"");
    }
}