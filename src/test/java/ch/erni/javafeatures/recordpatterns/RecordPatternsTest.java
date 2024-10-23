package ch.erni.javafeatures.recordpatterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordPatternsTest {

    @Test
    void verifyGetObjectDescriptionWorks() {

        var upperLeft = new RecordPatterns.ColoredPoint(new RecordPatterns.Point(1, 2), RecordPatterns.Color.RED);
        var lowerRight = new RecordPatterns.ColoredPoint(new RecordPatterns.Point(4, 3), RecordPatterns.Color.BLUE);
        RecordPatterns.Rectangle rectangle = new RecordPatterns.Rectangle(upperLeft, lowerRight);

        assertEquals("Point with ignored values", RecordPatterns.getObjectDescription(new RecordPatterns.Point(1, 2)));
        assertEquals("upperLeftPointX: 1 / lowerRightPointColor color: BLUE", RecordPatterns.getObjectDescription(rectangle));
        assertEquals("Object with value null", RecordPatterns.getObjectDescription(null));
        assertEquals("Integer 5", RecordPatterns.getObjectDescription(5));
        assertEquals("String starting with 'known': 'known test string'", RecordPatterns.getObjectDescription("known test string"));
        assertEquals("There is no description defined for this object: unknown test string", RecordPatterns.getObjectDescription("unknown test string"));
        assertEquals("There is no description defined for this object: 0.5", RecordPatterns.getObjectDescription(0.5));
    }

}
