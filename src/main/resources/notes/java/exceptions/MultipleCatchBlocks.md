# Using Multiple Catch Blocks

This Java code demonstrates how to handle different types of runtime errors using **multiple `catch` blocks**, providing more specific error messages and handling. The program attempts to perform two operations that could lead to exceptions: accessing an array element and then performing a division. The core logic is placed within a `try` block.

If the user enters an index that results in `numbers[index]` being `0` (e.g., if `numbers[0]` was `0`), an `ArithmeticException` occurs. This exception is caught by the first `catch (ArithmeticException e)` block, leading to a specific "You tried to divide by zero" message.

If the user enters an index outside the valid range (0, 1, 2), an `ArrayIndexOutOfBoundsException` occurs. This exception is caught by the second `catch (ArrayIndexOutOfBoundsException e)` block, providing a message about the index being out of range.

Finally, a more general `catch (Exception e)` block is included. This acts as a **fallback** for any other type of exception that might occur and isn't specifically caught by the preceding, more specialized `catch` blocks. 

It catches `Exception`, which is the superclass of almost all other exception types, ensuring that no unexpected exception goes unhandled. The order of `catch` blocks matters: more specific exceptions should be caught before more general ones to ensure they are handled appropriately.
