public class LogicalOperators {
    public static void main(String[] args) {
        int age = 20;
        boolean hasTicket = true;

        if (age >= 18 && hasTicket) {
            System.out.println("You can enter the concert.");
        } else {
            System.out.println("You cannot enter the concert.");
        }

        boolean isMember = false;
        if (age >= 18 || isMember) {
            System.out.println("You can join the club.");
        } else {
            System.out.println("You cannot join the club.");
        }

        boolean isClosed = false;
        if (!isClosed) {
            System.out.println("The store is open.");
        } else {
            System.out.println("The store is closed.");
        }
    }
}