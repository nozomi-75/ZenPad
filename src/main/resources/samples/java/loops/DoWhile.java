public class DoWhile {
    public static void main(String[] args) {
        int count = 1;

        do {
            System.out.println("Count is: " + count);
            count++;
        } while (count <= 5);

        System.out.println("Done counting!");
    }
}