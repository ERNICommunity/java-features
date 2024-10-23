# Stream Gatherers (Preview)
The Stream API provides a rich set of intermediate operations, such as map, filter, and reduce. However, these operations are not sufficient to express all possible data processing tasks. For example, consider the task of grouping elements into fixed-size groups, or transforming a stream of strings into a stream of integers. These tasks require custom intermediate operations that are not provided by the Stream API.

The proposed solution is to introduce a new intermediate operation, gather, which allows developers to define custom intermediate operations. A gatherer is an instance of the Gatherer interface, which defines four functions: initializer, integrator, combiner, and finisher. These functions work together to process the elements of a stream and produce a new stream.

### Built-in Gatherers
The Gatherers class provides several built-in gatherers that can be used to implement common data processing tasks. These gatherers include fold, mapConcurrent, scan, windowFixed, and windowSliding. These gatherers can be used to implement tasks such as aggregating elements, transforming elements, and grouping elements into fixed-size groups.

Example:
Here is an example of using the windowFixed gatherer to group elements into fixed-size groups:
```java
// This code will produce a list of lists, where each inner list contains three elements.
Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
.gather(Gatherers.windowFixed(3))
.toList();
```
The proposal to enhance the Stream API with custom intermediate operations will provide developers with more flexibility and expressiveness when processing data streams. The introduction of built-in gatherers will simplify the process of implementing common data processing tasks, and the Gatherer interface will provide a flexible way to define custom intermediate operations.

### Define own gatherer
Define gatherer interface for windowFixed:
```java
record WindowFixed<TR>(int windowSize)
    implements Gatherer<TR, ArrayList<TR>, List<TR>>
{

    public WindowFixed {
        // Validate input
        if (windowSize < 1)
            throw new IllegalArgumentException("window size must be positive");
    }

    @Override
    public Supplier<ArrayList<TR>> initializer() {
        // Create an ArrayList to hold the current open window
        return () -> new ArrayList<>(windowSize);
    }

    @Override
    public Integrator<ArrayList<TR>, TR, List<TR>> integrator() {
        // The integrator is invoked for each element consumed
        return Gatherer.Integrator.ofGreedy((window, element, downstream) -> {

            // Add the element to the current open window
            window.add(element);

            // Until we reach our desired window size,
            // return true to signal that more elements are desired
            if (window.size() < windowSize)
                return true;

            // When the window is full, close it by creating a copy
            var result = new ArrayList<TR>(window);

            // Clear the window so the next can be started
            window.clear();

            // Send the closed window downstream
            return downstream.push(result);

        });
    }

    // The combiner is omitted since this operation is intrinsically sequential,
    // and thus cannot be parallelized

    @Override
    public BiConsumer<ArrayList<TR>, Downstream<? super List<TR>>> finisher() {
        // The finisher runs when there are no more elements to pass from
        // the upstream
        return (window, downstream) -> {
            // If the downstream still accepts more elements and the current
            // open window is non-empty, then send a copy of it downstream
            if(!downstream.isRejecting() && !window.isEmpty()) {
                downstream.push(new ArrayList<TR>(window));
                window.clear();
            }
        };
    }
}
```
## Task
- Rewrite the methods in the file [StreamGatherers.java](StreamGatherers.java) 
- Use JDk 23 and wherever possible use stream gatherers for the solution
- Goal is that [the unit test for this class](StreamGatherersTest.java) passes again after your refactoring 

## Sources
- https://openjdk.org/jeps/473
- https://medium.com/javarevisited/the-future-of-java-exploring-the-new-features-of-java-23-05b9c508c8b8
