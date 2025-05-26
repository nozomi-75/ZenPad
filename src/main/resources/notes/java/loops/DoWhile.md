# The Do-while Loop

The **do-while loop** is a fundamental programming construct that allows a set of instructions to be executed repeatedly. What makes the `do-while loop` special is that it guarantees the code inside the loop will run at least once, even if the condition for repeating isn’t met right away.

In this program, we start with a variable called `count`, which is initially set to 1. The program enters the `do` block, and the code inside this block always runs first. It prints "Count is: " followed by the current value of `count`, and then increases `count` by 1. After executing the `do` block, the program checks the condition `while (count <= 5)`. If the value of `count` is less than or equal to 5, the loop repeats: it prints the current count, increments the value, and checks the condition again. 

This process continues until count becomes 6. At that point, the condition `count <= 5` is no longer true, so the loop stops. The program then moves on to the next line after the loop and prints "Done counting!" This demonstrates how a `do-while` loop ensures that the code inside the loop is executed at least once, regardless of the initial condition.
