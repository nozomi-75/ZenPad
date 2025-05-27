# Creating Methods with Parameters and Return Value

This Java code illustrates a core concept: a **method that returns a value**. Unlike `void` methods, the `add` method here has a specified return type of `int`, meaning it's designed to give back an integer value. Inside `add`, it takes two integer parameters, `a` and `b`, calculates their sum, and then uses the `return` keyword to send that `result` back to wherever the method was called from.

In the `main` method, `add(50, 25)` is called, and the integer value it returns (which is 75) is then stored in the `sumResult` variable. The example also shows that the returned value can be used directly, such as when `add(5, 6)` is called within the `println` statement. 
