# Type Casting

Sometimes, you'll need to convert data from one type to another. This is called **type casting**. Think of it like pouring liquid from one container to another, but you need to be careful about the size of the containers.

For example, we have an `int` (a whole number) named `myInt` that holds the value `42`. When we assign `myInt` to `myDouble`, Java automatically converts the `int` to a `double`. This is called **widening casting**. There is no rish of losing information, so Java does it for you automatically.

When you print, you'll see:

- `int value: 42`
- `double value: 42.0` (notice the `.0` because it's now a `double`)

Then, we have a `double` named `price` that holds the value `19.99`. We want to put this into an `int` (a whole number). This is called **narrowing casting** because you're moving from a "larger" container to a "smaller" container. There is a risk of losing information, so you need to be careful when doing this.

Because of this risk, Java won't do it automatically. You have to tell it explicitly that you know what you're doing by putting `(int)` in front of the `double` variable. This tells Java, "Hey, convert this `double` into an `int` for me, even if it means losing the decimal part."

When you print, you'll see:

- `double value: 19.99`
- `int value: 19` (the `.99` is lost)
