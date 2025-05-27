# Getter and Setter Methods

This Java code expands on the concept of encapsulation by introducing **getter and setter methods**, which are standard practices for controlled access to private class variables. In the `Pet` class, `name` and `age` remain `private`, meaning they cannot be accessed directly from outside the class.

To read the value of a private variable, a **getter method** is used. For instance, `public String getName()` allows other parts of the program to retrieve the pet's name, and `public int getAge()` retrieves its age. These methods typically just `return` the value of the corresponding private variable.

To modify the value of a private variable, a **setter method** is used. For example, `public void setName(String name)` allows you to set the pet's name. Notice that the `setAge(int age)` method includes a simple **validation**: `if (age > 0)`. 

This demonstrates a key advantage of setters: you can add logic to validate input before changing the internal state of the object, ensuring data integrity. Instead of directly allowing `cat.age = -5;`, the setter ensures only valid ages are set. In the `main` method, you can see these setters in action, like `cat.setAge(3)` and `cat.setName("Snow")`, which modify the `cat` object's private data in a controlled manner.
