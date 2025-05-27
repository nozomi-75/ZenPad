# Scanner `next()` method

This Java code demonstrates how to read **word-by-word input from the user** using the `Scanner` class. The line `import java.util.Scanner;` at the very top is an **import statement**. It's necessary because the `Scanner` class is part of the `java.util` package, and without this import, the program wouldn't know where to find the `Scanner` definition. 

Inside the `main` method, a `Scanner` object named `input` is created, configured to read from the standard input stream (`System.in`), which is typically the keyboard. When `input.next()` is called, it reads characters from the input until it encounters a whitespace character (like a space, tab, or newline), returning that sequence of characters as a `String`. 

Calling it again reads the next word. The program then displays the extracted first and last names. Finally, `input.close()` is called to release the resources associated with the scanner, which is good practice.
