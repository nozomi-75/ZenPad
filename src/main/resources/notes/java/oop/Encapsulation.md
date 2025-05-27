# Encapsulation in OOP

This Java code perfectly illustrates the core Object-Oriented Programming (OOP) concept of **Encapsulation**. Encapsulation is like putting related data (variables) and the methods that operate on that data (functions) into a single unit, which is a class in Java. More importantly, it involves restricting direct access to some of an object's components.

In this example, the `Pet` class encapsulates the `name` and `age` of a pet. Notice that these variables are declared as `private`. This is the key to encapsulation; it means `name` and `age` can only be accessed or modified from *within* the `Pet` class itself. You can't directly modify `dog.name` from outside the `Pet` class because `name` is private.

Instead of direct access, encapsulation promotes interaction through **public methods**. The `Pet` constructor is public, allowing you to create new `Pet` objects by passing in a name and age. The `displayInfo()` method is also public, providing a controlled way to *view* the pet's information.

This controlled access protects the data from accidental or unauthorized changes, ensuring the object's internal state remains consistent and valid. It's like having a secure box where the internal workings are hidden, and you interact with it only through specific, well-defined controls.
