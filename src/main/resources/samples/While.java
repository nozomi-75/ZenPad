public class While {
    public static void main(String[] args) {
        final int n = 10; // predefined, replaceable
        int sum = 0, i = 1; // don't replace

        while (i <= n) {
            sum += i; // sum = sum + i
            i++;
        }

        System.out.println("The sum of all numbers up to " + n + " is: " + sum);
    }
}