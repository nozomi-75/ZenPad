# Static vs Instance Methods

This Java code provides a fundamental distinction between **instance methods** and **static methods**. The `instanceMethod()` belongs to **objects** (or instances) of the `StaticVsInstance` class. To call it, you first need to create an object, like `myObject = new StaticVsInstance()`, and then use that object to invoke the method (`myObject.instanceMethod()`).

Each object can have its own state, and instance methods operate on that specific object's data. In contrast, `staticMethod()` belongs to the **class itself**, not to any particular object. You can call a static method directly using the class name (`StaticVsInstance.staticMethod()`), or simply by its name within the same class as shown.

Static methods cannot directly access instance variables or instance methods without an object reference, as they don't operate on specific object data. This example highlights the different ways these two types of methods are defined and invoked, reflecting their distinct roles in object-oriented programming.
