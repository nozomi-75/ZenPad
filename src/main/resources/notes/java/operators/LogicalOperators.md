# Logical Operators

We have an `age` (20) and a `hasTicket` status (`true`). Then we see three main logical operators in action:

**AND** (`&&`): This operator means "both conditions must be true." In the first example, you can enter the concert only if your `age` is 18 or over *AND* you `hasTicket`. If either condition is false, the whole statement is false.

**OR** (`||`): This operator means "at least one condition must be true." For joining the club, you can enter if your `age` is 18 or over *OR* if you `isMember`. Only if *both* conditions are false will you be denied entry.

**NOT** (`!`): This operator reverses a `true`/`false` value. If `isClosed` is `false`, then `!isClosed` becomes `true`, meaning "the store is open." It's like saying "it is NOT true that the store is closed."

The `if` and `else` statements then use these combined conditions to decide which message to print. It's like saying, "If this condition is satisfied, then do this. Else, do this." As you progress further, you will likely rely on logical operators in making decisions within your program. You will also learn more about `if` and `else`.
