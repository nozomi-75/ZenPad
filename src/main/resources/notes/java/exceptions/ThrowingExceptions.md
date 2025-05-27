# Throwing Exceptions in Java

This Java code demonstrates the concept of **throwing an exception** explicitly when a certain condition is not met, indicating an invalid state or argument. The program first prompts the user to enter their age, including some basic input validation to ensure an integer is provided. 

After successfully getting an integer age, it checks if the `age` is less than 18. This is where the core concept of throwing an exception comes into play. If `age < 18` is `true`, it means the input is valid as an integer but invalid according to the program's business logic (i.e., the user must be 18 or older).

In this scenario, instead of just printing an error message and continuing, the code uses the `throw new IllegalArgumentException("You must be 18 or older.");` statement. This line **creates a new `IllegalArgumentException` object** with a descriptive message and then **throws** it.

When an exception is thrown, the normal execution flow of the program is immediately halted, and the Java Virtual Machine (JVM) looks for a `catch` block that can handle this specific type of exception. If no `catch` block is found further up the call stack, the program will terminate with an unhandled exception, indicating a problem.
