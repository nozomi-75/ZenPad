# Traditional Switch Statement

This program uses a `switch` statement, which is a control structure designed to execute different blocks of code based on the **specific value** of a single variable. It's like having a menu where you pick an item, and something specific happens for that item. You cannot use it for a range of values (i.e. `day > 5`).

We have an `int` variable called `day`, set to `1`. The `switch (day)` statement evaluates the value of `day`. It then looks for a `case` that matches this value. Since `day` is `1`, it matches `case 1:`. The code inside `case 1:` executes, which prints "Monday". 

After printing "Monday", you see the `break;` keyword. This is very important! It tells the program to immediately exit the `switch` statement. Without it, the program would continue "falling through" and execute the code for `case 2`, `case 3`, and so on, until it finds a `break` or reaches the end of the `switch` block. This "fall-through" behavior is usually not what you want.

The `default` case is like an "else" for the `switch` statement. If none of the `case` values match the variable being switched on, the code inside the `default` block will execute. In this example, if `day` was, say, `9`, then the `default` case would print "...".

The `default` case typically comes at the very end of the `switch` statement. If the `default` case is the last block of code in the `switch`, there's no need for a `break;` because the program would naturally exit the `switch` statement anyway after executing the `default` code.

`switch` statements are very useful for making your code clean and readable when you have many possible outcomes based on a single variable's value. They are an alternative to long chains of `if-else` statements, making your logic easier to follow.
