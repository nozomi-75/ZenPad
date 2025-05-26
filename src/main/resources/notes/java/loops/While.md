# The While Loop

This program uses a **while loop** to calculate the sum of all whole numbers from 1 up to a specific number, in this case, 10. A while loop is a control structure that keeps repeating a block of code as long as a certain condition remains true. While loops are especially useful for tasks where you need to perform repetitive calculations or actions, and the number of repetitions isn't fixed or known beforehand.

In this example, a constant variable `n` is set to 10, which is the target number to sum up to. Two more variables are created: `sum`, initialized to 0 to store the running total, and `i` which is initialized to 1 to act as the counter, starting from the first number to add. The core of the loop is the condition `while (i <= n)`. 

Before each repetition, the program checks if the current value of `i` is less than or equal to `n`. If it is, the code inside the loop runs: the current value of `i` is added to `sum`, and then `i` is increased by 1. This process continues, with the loop executing as long as `i` is 10 or less, each time adding the current value of `i` to sum and then increasing `i`.

Eventually, `i` becomes 11. When the condition is checked again, it is no longer true, so the loop stops. The program then moves to the line after the loop and prints the final message, showing "The sum of all numbers up to 10 is: " followed by the calculated sum, which will be 55.
