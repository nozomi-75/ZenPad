# Adding a Finally to the Try-catch

This Java code demonstrates robust file reading using `try-catch-finally` blocks, a critical pattern for resource management, especially with I/O operations. It aims to read and print the contents of a file named "sample.txt" line by line.

Here's a breakdown:
- A `FileReader` is used to read character files. It's the basic component for reading text.
- A `BufferedReader` wraps around a `FileReader` (or any other `Reader`). It makes reading more efficient by buffering characters, allowing for faster reading of lines using `readLine()`.

The file reading logic is placed inside the `try` block. This is where potential `IOException`s can occur, such as if "sample.txt" doesn't exist or there are permission issues. If such an error happens, execution jumps to the `catch (IOException e)` block, which then prints an error message.

The `finally` block is crucial. Its code *always* executes, regardless of whether an exception occurred or not, or if a `return` statement was encountered in the `try` or `catch` blocks. This makes it the ideal place to perform cleanup operations, like closing resources. 

Here, it ensures that the `BufferedReader` (`br`) is closed. Closing resources is vital to prevent resource leaks and potential issues, especially with files. A nested `try-catch` block is used inside `finally` to handle potential `IOException` during the closing process itself, demonstrating thorough error handling.
