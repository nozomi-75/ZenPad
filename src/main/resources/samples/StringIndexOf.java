public class StringIndexOf {

    public static void main(String[] args) {
        String sentence = "Learning Java is fun. Java is versatile.";

        int indexOfJ = sentence.indexOf('J');
        int indexOfStringJava = sentence.indexOf("Java");
        int indexOfSecondJava = sentence.indexOf("Java", 10);
        int indexOfX = sentence.indexOf('X');

        System.out.println("The sentence is: \"" + sentence + "\"");
        System.out.println("Index of first 'J': " + indexOfJ);
        System.out.println("Index of first \"Java\": " + indexOfStringJava);
        System.out.println("Index of \"Java\" starting from index 10: " + indexOfSecondJava);
        System.out.println("Index of 'X': " + indexOfX);
    }
}