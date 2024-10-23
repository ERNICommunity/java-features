package ch.erni.javafeatures.recordpatterns;

public class RecordPatterns {

    record Point(int x, int y) {
    }

    enum Color {RED, GREEN, BLUE}

    interface Shape {
    }

    record ColoredPoint(Point pt, Color color) {
    }

    record Rectangle(ColoredPoint upperLeft, ColoredPoint lowerRight) implements Shape {
    }

    /**
     * Write the following method with JDK 23 using switch statement
     * use pattern matching and the suitable level of decomposition
     *
     * @param object input object to describe
     * @return String to describe object
     */
    static String getObjectDescription(Object object) {
        return switch (object) {
            case null -> "Object with value null";
            case Point ignored -> "Point with ignored values";
            case Rectangle(
                    ColoredPoint(Point(var upperLeftPointX, _), _),
                    ColoredPoint(Point(var _, _), var lowerRightPointColor)
            ) ->
                    String.format("upperLeftPointX: %s / lowerRightPointColor color: %s", upperLeftPointX, lowerRightPointColor);
            case int i -> String.format("Integer %d", i);
            case String s when s.startsWith("known") -> String.format("String starting with 'known': '%s'", s);
            default -> "There is no description defined for this object: " + object;
        };
    }

}
