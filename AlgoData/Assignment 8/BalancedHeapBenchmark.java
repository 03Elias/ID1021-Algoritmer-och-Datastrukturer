import java.util.PriorityQueue;
import java.util.Random;
import java.util.Arrays;

public class BalancedHeapBenchmark {
    public static void main(String[] args) {
        int numElements = 1023;
        int maxRandomValue = 10000;
        int minIncrement = 10;
        int maxIncrement = 100;
        int numPushOperations = 1000;

        long[] pushTimesScenario1 = new long[numPushOperations];
        long[] pushTimesScenario2 = new long[numPushOperations];

        Random random = new Random();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Scenario 1: Add 1023 elements with random values to the PriorityQueue
        for (int i = 0; i < numElements; i++) {
            int randomValue = random.nextInt(maxRandomValue + 1);
            priorityQueue.add(randomValue);
        }

        // Collect time taken for push operations in Scenario 1
        for (int i = 0; i < numPushOperations; i++) {
            int increment = random.nextInt(maxIncrement - minIncrement + 1) + minIncrement;
            long startTime = System.nanoTime();
            priorityQueue.add(priorityQueue.poll() + increment);
            long endTime = System.nanoTime();
            pushTimesScenario1[i] = endTime - startTime;
        }

        // Scenario 2: Dequeue the element with the highest priority, increment it, and add it back
        for (int i = 0; i < numPushOperations; i++) {
            int minElement = priorityQueue.poll();
            int incrementedElement = minElement + random.nextInt(maxIncrement - minIncrement + 1) + minIncrement;
            long startTime = System.nanoTime();
            priorityQueue.add(incrementedElement);
            long endTime = System.nanoTime();
            pushTimesScenario2[i] = endTime - startTime;
        }

        // Calculate the median times for push operations
        Arrays.sort(pushTimesScenario1);
        Arrays.sort(pushTimesScenario2);
        long medianTimeScenario1 = pushTimesScenario1[numPushOperations / 2];
        long medianTimeScenario2 = pushTimesScenario2[numPushOperations / 2];

        System.out.println("Scenario 1: Push Operations on Original Heap - Median Time: " + medianTimeScenario1 + " nanoseconds");
        System.out.println("Scenario 2: Push Operations on Updated Heap - Median Time: " + medianTimeScenario2 + " nanoseconds");
    }
}
