# Rule Switch Statement

This program also uses a **`switch` statement**, but it's a more modern version introduced in newer versions of Java. It serves the same purpose as the traditional `switch` we just discussed: to execute different code based on the value of a variable. The main difference is in its appearance and how it handles "fall-through."

Instead of:
`case 1: System.out.println("Monday"); break;`

You now see:
`case 1 -> System.out.println("Monday");`

The `->` arrow directly associates the `case` value with the code to be executed for that case. With the `->` syntax, the `break` keyword is **not necessary**. The program automatically executes only the code associated with the matching `case` and then exits the `switch` statement.

This eliminates the common "fall-through" errors that can happen with traditional `switch` statements if you forget a `break`. It makes your code cleaner, more concise, and less prone to errors by automatically handling the exit after a match. It's particularly useful when each case simply executes a single line of code or a small block. It offers a more readable and safer alternative to the classic `switch` statement for multi-way branching.