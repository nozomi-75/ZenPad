# Input Validation in Scanners

This Java code demonstrates how to **robustly read multiple integer inputs from the user** and then calculate their sum, incorporating input validation. It first declares an integer array to store five numbers. A `Scanner` object is set up for input. The program then enters a `for` loop to populate this array, prompting the user for each number.

The core of the input handling resides in the separate `getInput` method. This method takes a prompt message and the `Scanner` object as arguments. Inside `getInput`, a `while(true)` loop continuously prompts the user until valid integer input is provided. The `input.hasNextInt()` method is crucial here; it checks if the next token in the input stream can be interpreted as an integer *without actually reading it*. 

If it's a valid integer, `input.nextInt()` reads it, and the loop breaks. If not, an error message is displayed, and `input.next()` is called to consume the invalid input, preventing an infinite loop. After all numbers are collected, the code uses an enhanced `for` loop to easily sum them up. Finally, the total sum is displayed, and the `Scanner` is closed, ensuring proper resource management.
