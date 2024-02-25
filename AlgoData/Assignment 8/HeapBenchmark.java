import java.util.Random;
import java.util.Arrays;

public class HeapBenchmark {
    public static void main(String[] args) {
        int runs = 1000;
        int[] depthStatsPush = new int[runs];
        int[] depthStatsDequeueInsert = new int[runs];
        long[] executionTimesPush = new long[runs];
        long[] executionTimesDequeueInsert = new long[runs];
        Random rand = new Random();

        for (int i = 0; i < runs; i++) {
            BalancedHeap heapPush = new BalancedHeap();
            BalancedHeap heapDequeueInsert = new BalancedHeap();

            // Add 1023 elements with random values [0..10000]
            for (int j = 0; j < 1023; j++) {
                int value = rand.nextInt(10001);
                heapPush.add(value);
                heapDequeueInsert.add(value);
            }

            // Measure time for remove and add operations
            long startTimeDequeueInsert = System.nanoTime();
            int removedElement = heapDequeueInsert.remove();
            heapDequeueInsert.add(removedElement + rand.nextInt(91) + 10); // Add back the element with an increment
            long endTimeDequeueInsert = System.nanoTime();
            executionTimesDequeueInsert[i] = endTimeDequeueInsert - startTimeDequeueInsert;

            // Measure time for push operation
            long startTimePush = System.nanoTime();
            int incr = rand.nextInt(91) + 10; // [10..100] increment
            int depthPush = heapPush.push(incr);
            long endTimePush = System.nanoTime();
            depthStatsPush[i] = depthPush;
            executionTimesPush[i] = endTimePush - startTimePush;
        }

        // Calculate and print the median execution time for remove and add
        Arrays.sort(executionTimesDequeueInsert);
        long medianExecutionTimeDequeueInsert = executionTimesDequeueInsert[executionTimesDequeueInsert.length / 2];
        System.out.println("Median Execution Time (Remove and Add): " + medianExecutionTimeDequeueInsert + " ns");

        // Calculate and print the median depth statistics for push
        Arrays.sort(depthStatsPush);
        int medianDepthPush = depthStatsPush[depthStatsPush.length / 2];
        System.out.println("Median Depth (Push): " + medianDepthPush + " levels");

        // Calculate and print the median execution time for push
        Arrays.sort(executionTimesPush);
        long medianExecutionTimePush = executionTimesPush[executionTimesPush.length / 2];
        System.out.println("Median Execution Time (Push): " + medianExecutionTimePush + " ns");
    }
}
