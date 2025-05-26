public class PrintData {
    public static void main(String[] args) {
        String accountHolder = "Maria Santos";
        char accountType = 'S'; // S for Savings, C for Checking
        byte branchNumber = 12;
        short accountNumber = 3456;
        int bankCode = 123_456;
        long cardNumber = 1234_5678_9012_3456L;
        float interestRate = 2.5f;
        double accountBalance = 150_000.75;

        System.out.println("Bank Account Information");
        System.out.println("------------------------------");
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + accountType);
        System.out.println("Branch Number: " + branchNumber);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Bank Code: " + bankCode);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Account Balance: $" + accountBalance);
    }
}