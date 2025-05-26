# Ternary Operator

This program demonstrates the **ternary operator**, also known as the conditional operator. It's like a mini `if-else` statement that allows you to assign a value to a variable based on a condition, all in a single line.

Here's how it works:
`data_type var_name = condition ? value_if_true : value_if_false`

In this example, we have a variable `number` set to 10. The ternary operator decides the message for the `identify` variable:

1.  `number > 0`: This is the condition being checked. Is 10 greater than 0? Yes, this is `true`.
2.  `? "The number is positive."`: Because the condition was `true`, the string "The number is positive." is chosen and assigned to `identify`.
3.  `: "The number is negative or zero."`: This part would only be executed if the initial condition (`number > 0`) was `false`. In that case, `identify` would be set to "The number is negative or zero."

The ternary operator is excellent for making your code more compact when you need to assign a value based on a single condition. It's a handy tool for writing cleaner code for straightforward choices.

**You can also extend the ternary operator, as seen in the previous example.** This means you can chain multiple conditions, where the `value_if_false` part of one ternary operator becomes another complete ternary operator. This allows for more complex, multi-way decisions all within a single line, though it's often better to use traditional `if-else if-else` statements for readability when conditions become very complex.
