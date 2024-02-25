import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BinaryTreeBenchmark {
    public static void main(String[] args) {
        int[] datasetSizes = { 100, 500, 1000, 10000}; // Different dataset sizes to test

        for (int size : datasetSizes) {
            List<Long> combinedTimes = new ArrayList<>();

            // Perform the benchmark 1000 times
            for (int loop = 0; loop < 1000; loop++) {
                BinaryTree<Integer, Integer> tree = new BinaryTree<>();
                Random random = new Random();

                // Create a balanced dataset to ensure logarithmic behavior
                List<Integer> keys = new ArrayList<>();
                for (int i = 1; i <= size; i++) {
                    keys.add(i);
                }

                Collections.shuffle(keys, random); // Shuffle the keys

                // Start the timer for adding elements
                long addStartTime = System.nanoTime();

                // Add elements to the tree
                for (int key : keys) {
                    tree.add(key, key);
                }

                // Start the timer for lookup operations
                long lookupStartTime = System.nanoTime();

                // Perform lookup operations (e.g., 5000 times)
                for (int j = 0; j < 5000; j++) {
                    int keyToLookup = random.nextInt(size) + 1; // Random key within the dataset
                    tree.lookup(keyToLookup);
                }

                // Stop the timer for lookup operations
                long endTime = System.nanoTime();

                // Calculate the combined execution time (adding + lookup) in microseconds
                long combinedElapsedTime = (endTime - addStartTime + (endTime - lookupStartTime)) / 1000;
                combinedTimes.add(combinedElapsedTime);
            }

            // Calculate and print the median combined execution time for both operations in microseconds
            long medianCombinedTime = calculateMedian(combinedTimes);

            System.out.println("Dataset size: " + size);
            System.out.println("Median Combined Execution time (us): " + medianCombinedTime);
        }
    }

    // Method to calculate the median of a list of long values
    private static long calculateMedian(List<Long> values) {
        values.sort(null); // Sort the list in ascending order
        int size = values.size();

        if (size % 2 == 0) {
            // If the list has an even number of elements, take the average of the middle two values
            long middle1 = values.get(size / 2 - 1);
            long middle2 = values.get(size / 2);
            return (middle1 + middle2) / 2;
        } else {
            // If the list has an odd number of elements, return the middle value
            return values.get(size / 2);
        }
    }
}
