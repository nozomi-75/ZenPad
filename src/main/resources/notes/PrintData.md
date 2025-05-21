# Print Data

Just like with "Hello World!", our program lives inside a class. Think of PrintData as the specific name for this program's container.

The new part here is how we store different kinds of information using **variables**. Imagine variables as labeled boxes that hold different types of data.

Here, **String** means this box holds **text** (like names or sentences), and "Maria Santos" is the text we're putting inside the box named `accountHolder`. Notice the double quotes for text.

**char** is for a **single character**, like a letter. We put `'S'` (notice the single quotes) inside the box named `accountType`.

What follows are all different-sized boxes for **whole numbers**. **`byte`** is a small box for numbers from -128 to 127. **`short`** is a medium box, holding numbers from -32,768 to 32,767. **`int`** is a normal-sized box, capable of holding numbers from approximately -2 billion to 2 billion.

And **`long`** is a big box, for very large numbers ranging from approximately -9 quintillion to 9 quintillion (the `L` at the end of `cardNumber` tells Java it's a long number). We can use underscores to make our numbers easier to read.

Then, we have boxes for **numbers with decimal points**, which we often call floating-point numbers. `float` is for less precise decimals up to 7 places (the `f` tells Java it's a float), while `double` is generally used for more precise decimals up to 15 places and is often used for financial contexts.

Finally, we have multiple print statements. For example, it prints the text `"Account Holder: "` and then uses the `+` sign to `join` it with whatever is stored in the `accountHolder` variable (which is "Maria Santos"). The result on your screen will be `"Account Holder: Maria Santos"`.
