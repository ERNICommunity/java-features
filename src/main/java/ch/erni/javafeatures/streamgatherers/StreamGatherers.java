package ch.erni.javafeatures.streamgatherers;

import java.util.List;

public class StreamGatherers {

    /**
     * This method takes a list of integers as input and returns the multiplication result of all the integer
     * items in that list. If the list does not contain any integers, the function should return 1.
     *
     * @param integersToMultiply list with integers to multiply
     * @return multiplication result
     */
    static int multiplyIntegers(List<Integer> integersToMultiply) {
        int product = 1;
        for (Integer integer : integersToMultiply) {
            product *= integer;
        }
        return product;
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
        // Validate inputs
        if (numberOfSalesForEachDay == null || numberOfSalesForEachDay.isEmpty() || numberOfConsecutiveDays == 0) {
            throw new IllegalArgumentException("input argument is not correct");
        }

        int maxSum;
        int currentSum = 0;

        // Calculate the sum of the first 'numberOfConsecutiveDays' sales figures
        for (int i = 0; i < numberOfConsecutiveDays; i++) {
            if (i < numberOfSalesForEachDay.size()) {
                currentSum += numberOfSalesForEachDay.get(i);
            }
        }
        maxSum = currentSum; // Initialize maxSum with the first window sum

        // Calculate the rest of the sums
        for (int i = numberOfConsecutiveDays; i < numberOfSalesForEachDay.size(); i++) {
            currentSum += numberOfSalesForEachDay.get(i) - numberOfSalesForEachDay.get(i - numberOfConsecutiveDays);
            maxSum = Math.max(maxSum, currentSum); // Update maxSum if currentSum is greater
        }
        return maxSum;
    }

}
