# If-Else If-Else

This program uses an **if-else if-else structure**, which is a fundamental way for programs to perform different actions depending on whether certain conditions are met. It allows for a series of checks, one after another, until a true condition is found.

We start with a variable called `temperature`, set to `30`. The program then checks a series of conditions in order:

The first check is `if (temperature > 35)`. Since 30 is not greater than 35, this condition is false, and the code inside this specific block is skipped.

Next, the program moves to the `else if (temperature >= 20)` condition. Here, it checks if 30 is greater than or equal to 20. This condition is true. Because this condition is true, the program executes the code associated with it, printing "It's pleasant. A good day for a walk." After a condition in an if-else if-else chain is found to be true and its code executed, the rest of the chain is skipped.

Finally, there's the `else` block. This block acts as a catch-all. If none of the preceding `if` or `else if` conditions were true, the code within the `else` block would be executed. In this example, since an `else if` condition was met, the `else` block is skipped.

The if-else if-else structure is incredibly powerful because it enables your programs to react intelligently to different inputs and situations. It's fundamental for how programs decide everything.
