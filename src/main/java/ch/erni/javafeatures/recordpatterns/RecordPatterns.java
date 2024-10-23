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
        if (object == null) {
            return "Object with value null";
        }
        return switch (object) {
            case Point ignored -> "Point with ignored values";
            case Rectangle rectangle -> {
                ColoredPoint upperLeft = rectangle.upperLeft();
                ColoredPoint lowerRight = rectangle.lowerRight();
                int upperLeftPointX = upperLeft.pt().x();
                Color lowerRightPointColor = lowerRight.color();
                yield String.format("upperLeftPointX: %s / lowerRightPointColor color: %s",
                        upperLeftPointX, lowerRightPointColor);
            }
            case int i -> String.format("Integer %d", i);
            case String s -> {
                if (s.startsWith("known")) {
                    yield String.format("String starting with 'known': '%s'", s);
                } else {
                    yield "There is no description defined for this object: " + object;
                }
            }
            default -> "There is no description defined for this object: " + object;
        };
    }

}
