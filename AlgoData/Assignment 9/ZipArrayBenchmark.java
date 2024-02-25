import java.util.Arrays; 
public class ZipArrayBenchmark {

    public static void main(String[] args) {
        ZipArray zipTable = new ZipArray("postnummer.csv");
        int numberOfIterations = 10000;
        long[] lookupTimes11115 = new long[numberOfIterations];
        long[] binarySearchTimes11115 = new long[numberOfIterations];
        long[] lookupTimes98499 = new long[numberOfIterations];
        long[] binarySearchTimes98499 = new long[numberOfIterations];

        for (int i = 0; i < numberOfIterations; i++) {
            // Benchmark lookup for "11115"
            long startTimeLookup11115 = System.nanoTime();
            ZipArray.Node resultLookup11115 = zipTable.lookup("97235");
            long endTimeLookup11115 = System.nanoTime();
            lookupTimes11115[i] = endTimeLookup11115 - startTimeLookup11115;

            // Benchmark binary search for "11115"
            long startTimeBinary11115 = System.nanoTime();
            ZipArray.Node resultBinary11115 = zipTable.binarySearch("97235");
            long endTimeBinary11115 = System.nanoTime();
            binarySearchTimes11115[i] = endTimeBinary11115 - startTimeBinary11115;

            // Benchmark lookup for "98499"
            long startTimeLookup98499 = System.nanoTime();
            ZipArray.Node resultLookup98499 = zipTable.lookup("98499");
            long endTimeLookup98499 = System.nanoTime();
            lookupTimes98499[i] = endTimeLookup98499 - startTimeLookup98499;

            // Benchmark binary search for "98499"
            long startTimeBinary98499 = System.nanoTime();
            ZipArray.Node resultBinary98499 = zipTable.binarySearch("98499");
            long endTimeBinary98499 = System.nanoTime();
            binarySearchTimes98499[i] = endTimeBinary98499 - startTimeBinary98499;
        }

        // Sort the arrays to calculate the median
        Arrays.sort(lookupTimes11115);
        Arrays.sort(binarySearchTimes11115);
        Arrays.sort(lookupTimes98499);
        Arrays.sort(binarySearchTimes98499);

        long medianLookupTime11115 = lookupTimes11115[numberOfIterations / 2];
        long medianBinarySearchTime11115 = binarySearchTimes11115[numberOfIterations / 2];
        long medianLookupTime98499 = lookupTimes98499[numberOfIterations / 2];
        long medianBinarySearchTime98499 = binarySearchTimes98499[numberOfIterations / 2];

        System.out.println("Median Lookup Time for '11115': " + medianLookupTime11115 + " ns");
        System.out.println("Median Binary Search Time for '11115': " + medianBinarySearchTime11115 + " ns");
        System.out.println("Median Lookup Time for '98499': " + medianLookupTime98499 + " ns");
        System.out.println("Median Binary Search Time for '98499': " + medianBinarySearchTime98499 + " ns");
    }
}
