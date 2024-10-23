package ch.erni.javafeatures.streamgatherers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Gatherers;

public class StreamGatherers {

    /**
     * This method takes a list of integers as input and returns the multiplication result of all the integer
     * items in that list. If the list does not contain any integers, the function should return 1.
     *
     * @param integersToMultiply list with integers to multiply
     * @return multiplication result
     */
    static int multiplyIntegers(List<Integer> integersToMultiply) {
        return integersToMultiply.stream().gather(Gatherers.fold(() -> 1, (a, b) -> a * b))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("At least one element is needed for result"));
    }

    /**
     * You are given a list of daily sales figures for a store (input variable numberOfSalesForEachDay). Your task is to
     * write a method that calculates the maximum sum of sales over any number of consecutive days (input variable
     * numberOfConsecutiveDays).
     *
     * @param numberOfSalesForEachDay List of sales of multiple days
     * @param numberOfConsecutiveDays Number of consecutive days to calculate sum for
     * @return maximum sum from numberOfConsecutiveDays consecutive days in numberOfSalesForEachDay
     */
    static int maxTotalSalesInDays(List<Integer> numberOfSalesForEachDay, int numberOfConsecutiveDays) {
        return numberOfSalesForEachDay.stream()
                .gather(Gatherers.windowSliding(numberOfConsecutiveDays))
                .map(integers -> integers.stream().mapToInt(Integer::intValue).sum())
                .max(Comparator.comparingInt(i -> i))
                .orElseThrow(() -> new IllegalArgumentException("Number of days need to be more than 0"));

    }

}
