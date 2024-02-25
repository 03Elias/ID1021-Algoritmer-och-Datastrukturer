import java.util.Arrays;

public class ZipBenchmark {

    public static void main(String[] args) {
        Zip zipTable = new Zip("postnummer.csv");
        int numberOfIterations = 10000;
        long[] linearSearchTimes11115 = new long[numberOfIterations];
        long[] binarySearchTimes11115 = new long[numberOfIterations];
        long[] linearSearchTimes98499 = new long[numberOfIterations];
        long[] binarySearchTimes98499 = new long[numberOfIterations];

        for (int i = 0; i < numberOfIterations; i++) {
            // Benchmark search for "111 15" using linear search
            long startTimeLinear11115 = System.nanoTime();
            Zip.Node resultLinear11115 = zipTable.linear("111 15");
            long endTimeLinear11115 = System.nanoTime();

            linearSearchTimes11115[i] = endTimeLinear11115 - startTimeLinear11115;

            // Benchmark search for "984 99" using binary search
            long startTimeBinary98499 = System.nanoTime();
            Zip.Node resultBinary98499 = zipTable.binary("984 99");
            long endTimeBinary98499 = System.nanoTime();

            binarySearchTimes98499[i] = endTimeBinary98499 - startTimeBinary98499;

            // Benchmark search for "111 15" using linear search
            long startTimeLinear98499 = System.nanoTime();
            Zip.Node resultLinear98499 = zipTable.linear("984 99");
            long endTimeLinear98499 = System.nanoTime();

            linearSearchTimes98499[i] = endTimeLinear98499 - startTimeLinear98499;

            // Benchmark search for "984 99" using binary search
            long startTimeBinary11115 = System.nanoTime();
            Zip.Node resultBinary11115 = zipTable.binary("111 15");
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

        System.out.println("Median Linear Search Time for '111 15': " + medianLinearSearchTime11115 + " ns");
        System.out.println("Median Binary Search Time for '111 15': " + medianBinarySearchTime11115 + " ns");
        System.out.println("Median Linear Search Time for '984 99': " + medianLinearSearchTime98499 + " ns");
        System.out.println("Median Binary Search Time for '984 99': " + medianBinarySearchTime98499 + " ns");
    }
}
