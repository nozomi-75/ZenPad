# Scanner `hasNext()` method

This Java code demonstrates how to continuously read input from the user, **word by word**, until a specific termination command is given, leveraging the `Scanner` class and its `hasNext()` method. After setting up the `Scanner`, the program enters a `while` loop controlled by `input.hasNext()`. This method is crucial because it checks if there is **more input available** to be read. 

As long as the user keeps typing and hitting enter, `hasNext()` will return `true`, allowing the loop to continue. Inside the loop, `input.next()` reads the next word. The program includes a condition to `break` out of the loop if the user types "quit" (case-insensitively).
