import java.util.Arrays;

public class UpdatedZipBench {

    public static void main(String[] args) {
        UpdatedZip zipTable = new UpdatedZip("postnummer.csv");
        int numberOfIterations = 10000;
        long[] linearSearchTimes11115 = new long[numberOfIterations];
        long[] binarySearchTimes11115 = new long[numberOfIterations];
        long[] linearSearchTimes98499 = new long[numberOfIterations];
        long[] binarySearchTimes98499 = new long[numberOfIterations];

        for (int i = 0; i < numberOfIterations; i++) {
            // Benchmark search for "11115" using linear search
            long startTimeLinear11115 = System.nanoTime();
            UpdatedZip.Node resultLinear11115 = zipTable.linear(11115);
            long endTimeLinear11115 = System.nanoTime();

            linearSearchTimes11115[i] = endTimeLinear11115 - startTimeLinear11115;

            // Benchmark search for "98499" using binary search
            long startTimeBinary98499 = System.nanoTime();
            UpdatedZip.Node resultBinary98499 = zipTable.binary(98499);
            long endTimeBinary98499 = System.nanoTime();

            binarySearchTimes98499[i] = endTimeBinary98499 - startTimeBinary98499;

            // Benchmark search for "11115" using linear search
            long startTimeLinear98499 = System.nanoTime();
            UpdatedZip.Node resultLinear98499 = zipTable.linear(98499);
            long endTimeLinear98499 = System.nanoTime();

            linearSearchTimes98499[i] = endTimeLinear98499 - startTimeLinear98499;

            // Benchmark search for "98499" using binary search
            long startTimeBinary11115 = System.nanoTime();
            UpdatedZip.Node resultBinary11115 = zipTable.binary(11115);
            long endTimeBinary11115 = System.nanoTime();

            binarySearchTimes11115[i] = endTimeBinary11115 - startTimeBinary11115;
        }

        // Sort the arrays to calculate the median
        Arrays.sort(linearSearchTimes11115);
        Arrays.sort(binarySearchTimes11115);
        Arrays.sort(linearSearchTimes98499);
        Arrays.sort(binarySearchTimes98499);

        long medianLinearSearchTime11115 = linearSearchTimes11115[numberOfIterations / 2];
        long medianBinarySearchTime11115 = binarySearchTimes11115[numberOfIterations / 2];
        long medianLinearSearchTime98499 = linearSearchTimes98499[numberOfIterations / 2];
        long medianBinarySearchTime98499 = binarySearchTimes98499[numberOfIterations / 2];

        System.out.println("Median Linear Search Time for '11115': " + medianLinearSearchTime11115 + " ns");
        System.out.println("Median Binary Search Time for '11115': " + medianBinarySearchTime11115 + " ns");
        System.out.println("Median Linear Search Time for '98499': " + medianLinearSearchTime98499 + " ns");
        System.out.println("Median Binary Search Time for '98499': " + medianBinarySearchTime98499 + " ns");
    }
}
