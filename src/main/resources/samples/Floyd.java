public class Floyd {
    public static void main(String [] args) {
        int rows = 10;
        int number = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(number + "\t");
                number++;
            }
            System.out.println();
        }
    }
}