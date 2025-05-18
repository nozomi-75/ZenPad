public class MethodWithReturnValue {
    public static int add(int a, int b) {
        int result = a + b;
        return result;
    }

    public static void main(String[] args) {
        int sumResult = add(50, 25);
        System.out.println("The sum is " + sumResult);
        System.out.println("The sum is " + add(5, 6));
    }
}