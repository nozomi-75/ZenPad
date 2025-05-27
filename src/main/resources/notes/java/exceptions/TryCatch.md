# Handling Exceptions with Try-catch

This Java code demonstrates fundamental **exception handling** using a `try-catch` block, specifically to manage a potential division-by-zero error. An **exception** is an event that disrupts the normal flow of a program's instructions. It's essentially an error that occurs during the execution of a program.

Here, the program takes two integer inputs from the user: a dividend and a divisor. The core logic of the division is placed inside the `try` block. This block contains the code that might throw an exception, meaning it's where an unexpected problem could occur. If the `divisor` entered by the user is `0`, performing `dividend / divisor` will trigger an `ArithmeticException`—this is the specific type of exception that signals an illegal arithmetic operation like division by zero. 

When this `ArithmeticException` occurs, the normal flow of the `try` block is stopped, and the program immediately jumps to the `catch` block. The `catch (ArithmeticException e)` part specifies that this block will execute only if an `ArithmeticException` occurs. Inside the `catch` block, an informative error message is printed, gracefully handling the erroneous situation instead of letting the program crash.
