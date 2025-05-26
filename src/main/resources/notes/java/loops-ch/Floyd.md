# Floyd Triangle

This Java code constructs a **Floyd's Triangle**, a classic pattern where numbers are arranged in a right-angled triangle. The program starts by defining the total number of `rows` the triangle will have and initializes a `number` variable to 1, which will be the first value printed. 

An **outer `for` loop** iterates from 1 up to the specified number of `rows`, handling one row of the triangle in each iteration. Inside this, an **inner `for` loop** is responsible for printing the numbers within the current row. This inner loop runs `i` times, meaning for row 1 it prints one number, for row 2 it prints two numbers, and so on. 

In each iteration of the inner loop, the current value of `number` is printed, followed by a tab for spacing, and then `number` is incremented so the next print will be the subsequent integer. After the inner loop completes for a given row, `System.out.println()` moves the cursor to the next line, ensuring the numbers for the next row start on a new line, thus forming the triangular shape.