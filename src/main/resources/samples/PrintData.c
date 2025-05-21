#include <stdio.h>

int main() {
    char accountHolder[] = "Maria Santos";
    char accountType = 'S';
    signed char branchNumber = 12;
    short accountNumber = 3456;
    int bankCode = 123456;
    long cardNumber = 1234567890123456L;
    float interestRate = 2.5f;
    double accountBalance = 150000.75;

    printf("Bank Account Information\n");
    printf("------------------------------\n");
    printf("Account Holder: %s\n", accountHolder);
    printf("Account Type: %c\n", accountType);
    printf("Branch Number: %hhd\n", branchNumber);
    printf("Account Number: %hd\n", accountNumber);
    printf("Bank Code: %d\n", bankCode);
    printf("Card Number: %ld\n", cardNumber);
    printf("Interest Rate: %.1f%%\n", interestRate);
    printf("Account Balance: $%.2f\n", accountBalance);

    return 0;
}