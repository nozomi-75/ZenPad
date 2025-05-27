# Inheritance in OOP

This Java code perfectly demonstrates **Inheritance**, a fundamental concept in Object-Oriented Programming (OOP) that promotes code reusability and establishes a natural hierarchy between classes. Here, `Pet` is defined as the **superclass** (or parent class), containing common attributes (`name`, `age`) and behaviors (`eat()`, `sleep()`) that all pets share.

The `Dog` and `Cat` classes are **subclasses** (or child classes) that `extend` the `Pet` class. The `extends` keyword signifies this inheritance relationship. Because `Dog` and `Cat` extend `Pet`, they automatically **inherit** all the public and protected members (variables and methods) of the `Pet` class. This means a `Dog` object automatically has a `name`, an `age`, and can `eat()` and `sleep()` without these being explicitly defined in the `Dog` class itself.

The `super(name, age)` call within the `Dog` and `Cat` constructors is crucial. It invokes the constructor of the `Pet` superclass, ensuring that the inherited `name` and `age` attributes are properly initialized when a `Dog` or `Cat` object is created. This hierarchy allows for efficient code organization: common functionalities are defined once in the superclass, and subclasses can then build upon or specialize those functionalities without redundant code.
