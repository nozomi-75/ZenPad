package zenpad;

import java.util.Arrays;
import java.util.List;

/**
 * Provides a static list of sample {@link Branch} objects representing
 * categorized example files for use in the ZenPad application.
 * <p>
 * Each {@code Branch} contains a parent category, a base directory,
 * display names, file names, and optional note files for documentation.
 * </p>
 * <p>
 * This class is intended to centralize and organize the available sample
 * code and notes for display in the file opener panel or similar UI components.
 * </p>
 */
public class SampleBranches {
    public static List<Branch> getBranches() {
        return Arrays.asList(
            new Branch(
                "Java Basics",
                "/java/basics/",
                new String[] { "Hello world", "Data types", "Type casting", "True or false" },
                new String[] { "HelloWorld.java", "PrintData.java", "TypeCast.java", "Boolean.java" },
                new String[] { "HelloWorld.md", "PrintData.md", "TypeCast.md", "Boolean.md" }
            ),

            new Branch(
                "Java Operators",
                "/java/operators/",
                new String[] { "Arithmetic operators", "Assignment operators", "Relational operators", "Logical operators" },
                new String[] { "ArithmeticOperators.java", "AssignOperators.java", "RelationalOperators.java", "LogicalOperators.java" },
                new String[] { "ArithmeticOperators.md", "AssignOperators.md", "RelationalOperators.md", "LogicalOperators.md" }
            ),

            new Branch(
                "Java Conditions",
                "/java/conditions/",
                new String[] { "Else if structure", "Ternary operator", "Traditional switch", "Rule/modern switch" },
                new String[] { "ElseIf.java", "Ternary.java", "Switch.java", "RuleSwitch.java" },
                new String[] { "ElseIf.md", "Ternary.md", "Switch.md", "RuleSwitch.md" }
            ),

            new Branch(
                "Java Loops",
                "/java/loops/",
                new String[] { "For loop", "While loop", "Do-while loop", "Nested loops" },
                new String[] { "ForLoop.java", "While.java", "DoWhile.java", "NestedLoop.java" },
                new String[] { "ForLoop.md", "While.md", "DoWhile.md", "NestedLoop.md" }
            ),

            new Branch(
                "Java Loops Challenge",
                "/java/loops-ch/",
                new String[] { "Floyd's triangle", "Multiplication table", "Right triangle", "Sum of a matrix", "Coordinates" },
                new String[] { "Floyd.java", "MultiplicationTable.java", "RightTriangle.java", "MatrixSum.java", "PlotCoordinates.java" },
                new String[] { "Floyd.md", "MultiplicationTable.md", "RightTriangle.md", "MatrixSum.md", "PlotCoordinates.md" }
            ),

            new Branch(
                "Java Arrays",
                "/java/arrays/",
                new String[] { "Creating an array", "Array length property", "Enhanced for-loop", "Multidimensional array" },
                new String[] { "CreateArrays.java", "ThroughFor.java", "ForEach.java", "MtdArray.java" },
                new String[] { "CreateArrays.md", "ThroughFor.md", "ForEach.md", "MtdArray.md" }
            ),

            new Branch(
                "Java String Methods",
                "/java/string-methods/",
                new String[] { "String length()", "String isEmpty()", "String case()", "String indexOf()",
                "String contains()", "String substring()", "String equals()", "String replace()", "String trim()" },
                new String[] { "StringLength.java", "StringIsEmpty.java", "StringCaseChange.java", "StringIndexOf.java",
                "StringContains.java", "StringSubstring.java", "StringEquals.java", "StringReplace.java", "StringTrim.java" },
                new String[] { "StringLength.md", "StringIsEmpty.md", "StringCaseChange.md", "StringIndexOf.md",
                "StringContains.md", "StringSubstring.md", "StringEquals.md", "StringReplace.md", "StringTrim.md" }
            ),

            new Branch(
                "Java Methods",
                "/java/methods/",
                new String[] { "Simple method", "Method with parameters", "Method with return value", "Method with return only", "Static vs instance" },
                new String[] { "VoidMethod.java", "MethodWithParameters.java", "MethodWithReturnValue.java", "MethodReturnOnly.java", "StaticVsInstance.java" },
                new String[] { "VoidMethod.md", "MethodWithParameters.md", "MethodWithReturnValue.md", "MethodReturnOnly.md", "StaticVsInstance.md" }
            ),

            new Branch(
                "Java Scanner",
                "/java/scanner/",
                new String[] { "Scanner next()", "Scanner nextLine()", "Scanner hasNext()", "Scanner numbers" },
                new String[] { "ScannerNext.java", "ScannerNextLine.java", "ScannerHasNext.java", "ScannerNumbers.java" },
                new String[] { "ScannerNext.md", "ScannerNextLine.md", "ScannerHasNext.md", "ScannerNumbers.md" }
            ), 

            new Branch(
                "Java Exception Handling",
                "/java/exceptions/",
                new String[] { "Try-catch", "Try-catch-finally", "Multiple catch blocks", "Throwing exceptions" },
                new String[] { "TryCatch.java", "TryCatchFinally.java", "MultipleCatchBlocks.java", "ThrowingExceptions.java" },
                new String[] { }
            ),

            new Branch(
                "Java Object-Oriented",
                "/java/oop/",
                new String[] { "Encapsulation", "Getters and setters", "Inheritance", "Polymorphism", "Abstraction" },
                new String[] { "Encapsulation.java", "GetterSetter.java", "Inheritance.java", "Polymorphism.java", "Abstraction.java" },
                new String[] { }
            ),

            new Branch(
                "Other Python Examples",
                "/python/basics/",
                new String[] { "Hello world", "Data types" },
                new String[] { "HelloWorld.py", "PrintData.py" },
                new String[] { "HelloWorld.md", "PrintData.md" }
            ),

            new Branch(
                "Other C Examples",
                "/clang/basics/",
                new String[] { "Hello world", "Data types" },
                new String[] { "HelloWorld.c", "PrintData.c" },
                new String[] { "HelloWorld.md", "PrintData.md" }
            )

            // Add more branches here as needed
            // new Branch("Another Branch", new String[] {...}, new String[] {...})
        );
    }
}
