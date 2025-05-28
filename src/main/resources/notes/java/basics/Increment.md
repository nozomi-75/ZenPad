# Increments

This Java code clearly demonstrates the difference between **pre-increment (`++variable`)** and **post-increment (`variable++`)** operators, which are crucial for understanding how variable values change within expressions.

In the **pre-increment** example, `b = ++a;`, the `a` variable is incremented *first* (from 5 to 6), and *then* its new value (6) is assigned to `b`. This results in both `a` and `b` holding the value 6.

In the **post-increment** example, `y = x++;`, the `x` variable's *original value* (5) is assigned to `y` *first*, and *then* `x` is incremented (from 5 to 6). This leads to `y` holding 5, while `x` becomes 6. The subsequent print statement for `x` further confirms its updated value. This distinction is vital as it affects the order of operations and the values assigned in an expression.
