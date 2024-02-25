import java.util.Random;
import java.util.Arrays;

public class ArrayHeapBenchmark {
    public static void main(String[] args) {
        int runs = 10000; // Change runs to 10000
        long[] executionTimesRemoveAdd = new long[runs]; // Use long to store execution times
        long[] executionTimesPush = new long[runs];
        Random rand = new Random();

        for (int i = 0; i < runs; i++) {
            MinHeap minHeapRemoveAdd = new MinHeap(1023);
            MinHeap minHeapPush = new MinHeap(1023);

            // Add 1023 elements with random values [0..10000] to the MinHeap
            for (int j = 0; j < 1023; j++) {
                int value = rand.nextInt(10001);
                minHeapRemoveAdd.add(value);
                minHeapPush.add(value);
            }

            // Measure time for removing and adding using MinHeap
            long startTimeRemoveAdd = System.nanoTime();
            int removedElement = minHeapRemoveAdd.remove();
            minHeapRemoveAdd.add(removedElement);
            long endTimeRemoveAdd = System.nanoTime();
            executionTimesRemoveAdd[i] = endTimeRemoveAdd - startTimeRemoveAdd;

            // Measure time for push operations using MinHeap
            int incr = rand.nextInt(91) + 10; // [10..100] increment
            long startTimePush = System.nanoTime();
            int depthPush = minHeapPush.push(incr);
            long endTimePush = System.nanoTime();
            executionTimesPush[i] = endTimePush - startTimePush;
        }

        // Calculate and print the median execution time for remove and add
        Arrays.sort(executionTimesRemoveAdd);
        long medianExecutionTimeRemoveAdd = executionTimesRemoveAdd[executionTimesRemoveAdd.length / 2];
        System.out.println("Median Execution Time (Remove-Add): " + medianExecutionTimeRemoveAdd + " ns");

        // Calculate and print the median execution time for push
        Arrays.sort(executionTimesPush);
        long medianExecutionTimePush = executionTimesPush[executionTimesPush.length / 2];
        System.out.println("Median Execution Time (Push): " + medianExecutionTimePush + " ns");
    }
}
