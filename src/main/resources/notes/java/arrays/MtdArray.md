# Multidimensional Arrays

This Java code demonstrates how to **iterate through and print the elements of a two-dimensional array**, also known as a **matrix**, using nested `for` loops. The `matrix` is initialized with three rows and three columns of integer values. The **outer loop**, controlled by `i`, iterates through each **row** of the matrix. Crucially, `matrix.length` provides the number of rows. 

The **inner loop**, controlled by `j`, iterates through each **element within the current row**. Here, `matrix[i].length` dynamically retrieves the number of columns in the *current* row, allowing for potentially irregular matrices. This nested loop structure ensures that every element in the two-dimensional array is accessed and printed, with a new line after each row to maintain the matrix's visual structure.
