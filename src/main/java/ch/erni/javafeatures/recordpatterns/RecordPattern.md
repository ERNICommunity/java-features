# Record Patterns

Enhance the Java programming language with record patterns to deconstruct record values. Record patterns and type patterns can be nested to enable a powerful, declarative, and composable form of data navigation and processing.

Record patterns were added as preview feature in JDK 19, providing an easier way of extracting data from records, which are part of JDK since version 16. The functionality was further enhanced in JDK 21.

With JDK 21 followed pattern matching in switch statements, beneath other improvements this increased the overall safety of switch statements by requiring that pattern switch statements cover all possible input values.

## Usage and examples
### Pattern matching & records
Use type patterns to test whether a value is an instance of the record class Point and, if so, extract the x and y components from the value directly, invoking the accessor methods on our behalf
```java
static void printSum(Object obj) {
    // hint: Point(int x, int y) is a record pattern.
    if (obj instanceof Point(int x, int y)) {
        System.out.println(x+y);
    }
}
```
### Nested pattern matching
The true power of pattern matching is that it scales elegantly to match more complicated object graphs:
```java
record Point(int x, int y) {}
record Coordinate(Point p, Point q) {}
record Rectangle(Coordinate upperLeft, Coordinate lowerRight) {}

static void printUpperLeftCoordinate(Rectangle r) {
    if (r instanceof Rectangle(Coordinate ul, Coordinate lr)) {
        System.out.println(ul.p());
    }
}
```
But can we decompose more? Point is a record as well...
```java
record Point(int x, int y) {}
record Coordinate(Point p, Point q) {}
record Rectangle(Coordinate upperLeft, Coordinate lowerRight) {}

static void printUpperLeftCoordinate(Rectangle r) {
    if (r instanceof Rectangle(Coordinate(Point p, Point q), Coordinate lr)) {
        System.out.println(p);
    }
}
```
And more?
```java
record Point(int x, int y) {}
record Coordinate(Point p, Point q) {}
record Rectangle(Coordinate upperLeft, Coordinate lowerRight) {}

static void printUpperLeftCoordinate(Rectangle r) {
    if (r instanceof Rectangle(Coordinate(Point(var x, var y), Point q), Coordinate lr)) {
        System.out.println("Point (" + x + ", " + y +")");
    }
}
```
Depending on which extraction level we need, we can decompose more or less levels.

## Pattern matching & switch statements
Since JDK 21 we can use a switch statement instead of if/else clauses with instanceof to differentiate between object types. 
```java
// Prior to Java 21
static String formatter(Object obj) {
String formatted = "unknown";
if (obj instanceof Integer i) {
formatted = String.format("int %d", i);
} else if (obj instanceof Long l) {
formatted = String.format("long %d", l);
} else if (obj instanceof Double d) {
formatted = String.format("double %f", d);
} else if (obj instanceof String s) {
formatted = String.format("String %s", s);
}
return formatted;
}
```
```java
// As of Java 21
static String formatterPatternSwitch(Object obj) {
    return switch (obj) {
        case Integer i -> String.format("int %d", i);
        case Long l    -> String.format("long %d", l);
        case Double d  -> String.format("double %f", d);
        case String s  -> String.format("String %s", s);
        default        -> obj.toString();
    };
}
```
Another useful feature is the concept of 'guarded pattern case labels' where we can define optional guards (boolean expressions) to follow the pattern label.
```java
// As of Java 21
static void testNew(Object obj) {
    switch (obj) {
        case String s when s.length() == 1 -> ...
        case String s                      -> ...
        ...
    }
}
```
## Task
- Rewrite the Method getObjectDescription in the file [RecordPatterns.java](RecordPatterns.java)
- Use JDK 23 and wherever possible features which were mentioned above (switch statement, pattern matching, decomposition)
- Goal is that [the Unit Test for this class](RecordPatternsTest.java) passes again after your refactoring

## Quellen
- https://openjdk.org/jeps/440
- https://entwickler.de/java/javaneuenzehn-recordpatterns-patternmatching
- https://inside.java/2023/11/06/sip087/
- https://openjdk.org/jeps/441