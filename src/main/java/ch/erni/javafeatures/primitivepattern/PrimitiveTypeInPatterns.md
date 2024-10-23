# Primitive Types in Patterns, instanceof, and switch (Preview)
JEP 455 introduces the support for primitive types in pattern matching contexts, including instanceof and switch, enabling developers to use patterns with both reference and primitive types. This feature, introduced in Java 23 as a preview, enhances the expressiveness and uniformity of the Java language.

**Why It Matters:** By removing limitations on primitive types in pattern matching and expanding instanceof and switch, Java developers can write more concise, safer, and uniform code. This aligns Java's type handling across primitives and references and enhances data exploration.

## Usage and examples
```java
// no casts needed
if (obj instanceof String s && s.length() >= 5) {
    System.out.println(s.toUpperCase());
} else if (obj instanceof Integer i) {
    System.out.println(i * i);
} else {
    System.out.println(obj);
}
```
These changes simplify working with primitive types in complex data structures and improve type safety by rejecting illegal values, ensuring that Java continues to evolve in terms of both power and simplicity.

More reading here: https://www.happycoders.eu/de/java/java-23-features/#Primitive_Types_in_Patterns_instanceof_and_switch_Preview_-_JEP_455

## Sources
- https://openjdk.org/jeps/455
- https://medium.com/javarevisited/the-future-of-java-exploring-the-new-features-of-java-23-05b9c508c8b8
- https://www.happycoders.eu/java/java-23-features/#Primitive_Types_in_Patterns_instanceof_and_switch_Preview_-_JEP_455