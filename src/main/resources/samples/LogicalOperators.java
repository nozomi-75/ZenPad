public class LogicalOperators {
    public static void main(String[] args) {
        int age = 20;
        boolean hasTicket = true;

        // AND operator (&&)
        if (age >= 18 && hasTicket) {
            System.out.println("You can enter the concert.");
        } else {
            System.out.println("You cannot enter the concert.");
        }

        // OR operator (||)
        boolean isMember = false;
        if (age >= 18 || isMember) {
            System.out.println("You can join the club.");
        } else {
            System.out.println("You cannot join the club.");
        }

        // NOT operator (!)
        boolean isClosed = false;
        if (!isClosed) {
            System.out.println("The store is open.");
        } else {
            System.out.println("The store is closed.");
        }
    }
}