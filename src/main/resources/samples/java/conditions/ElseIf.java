public class ElseIf {
    public static void main(String[] args) {
        int temperature = 30;
        if (temperature > 35) {
            System.out.println("It's hot outside! Time for ice cream.");
        } else if (temperature >= 20) {
            System.out.println("It's pleasant. A good day for a walk.");
        } else {
            System.out.println("It's a bit chilly. Grab a jacket!");
        }
    }
}
