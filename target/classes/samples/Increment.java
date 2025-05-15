package samples;

public class Increment {
    public static void main(String[] args) {
        // Pre-increment
        int a, b;
        a = 5;
        b = ++a;
        System.out.println(a);
        System.out.println(b);
        
        System.out.println();
        
        // Post-increment
        int x, y;
        x = 5;
        y = x++;
        System.out.println(x);
        System.out.println(y);
        System.out.println(x);
    }
}
