package ch.erni.javafeatures.streamgatherers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StreamGatherersTest {

    @Test
    void verifyMultiplyIntegersWorks() {
        assertEquals(120, StreamGatherers.multiplyIntegers(List.of(5, 1, 2, 4, 3)));
        assertEquals(120, StreamGatherers.multiplyIntegers(List.of(1, 2, 3, 4, 5)));
        assertEquals(1800, StreamGatherers.multiplyIntegers(List.of(100, 2, 9)));
        assertEquals(1, StreamGatherers.multiplyIntegers(List.of(1)));
        assertEquals(1, StreamGatherers.multiplyIntegers(List.of()));
    }

    @Test
    void verifyMaxTotalSalesInDaysWorks() {
        List<Integer> listOfSales1 = List.of(100, 200, 300, 400, 500);
        assertEquals(900, StreamGatherers.maxTotalSalesInDays(listOfSales1, 2),
                "Should return sum of 400 and 500");

        List<Integer> listOfSales2 = List.of(900, 100, 900, 400, 500);
        assertEquals(1300, StreamGatherers.maxTotalSalesInDays(listOfSales2, 2),
                "Should return sum of 400 and 900");
        assertEquals(2800, StreamGatherers.maxTotalSalesInDays(listOfSales2, 5),
                "Should return sum of all items");
        assertEquals(2800, StreamGatherers.maxTotalSalesInDays(listOfSales2, 10),
                "Should return sum of all items");
        assertThrows(IllegalArgumentException.class,
                () -> StreamGatherers.maxTotalSalesInDays(listOfSales2, 0));

    }
}
