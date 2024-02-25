import java.util.Arrays;

public class SortingBenchmark {
    public static long benchmarkSelectionSort(int[] array) {
        long[] executionTimes = new long[5];
        for (int i = 0; i < 5; i++) {
            int[] copy = array.clone();
            long startTime = System.nanoTime();
            SelectionSort.selectionSort(copy);
            long endTime = System.nanoTime();
            executionTimes[i] = (endTime - startTime) / 1000; // Convert to microseconds
        }
        Arrays.sort(executionTimes);
        return executionTimes[2]; // Median value
    }

    public static long benchmarkInsertionSort(int[] array) {
        long[] executionTimes = new long[5];
        for (int i = 0; i < 5; i++) {
            int[] copy = array.clone();
            long startTime = System.nanoTime();
            InsertionSort.insertionSort(copy);
            long endTime = System.nanoTime();
            executionTimes[i] = (endTime - startTime) / 1000; // Convert to microseconds
        }
        Arrays.sort(executionTimes);
        return executionTimes[2]; // Median value
    }

    public static long benchmarkMergeSort(int[] array) {
        long[] executionTimes = new long[5];
        for (int i = 0; i < 5; i++) {
            int[] copy = array.clone();
            long startTime = System.nanoTime();
            MergeSort.mergeSort(copy);
            long endTime = System.nanoTime();
            executionTimes[i] = (endTime - startTime) / 1000; // Convert to microseconds
        }
        Arrays.sort(executionTimes);
        return executionTimes[2]; // Median value
    }

    public static long benchmarkOptimizedMergeSort(int[] array) {
        long[] executionTimes = new long[5];
        for (int i = 0; i < 5; i++) {
            int[] copy = array.clone();
            long startTime = System.nanoTime();
            OptimizedMergeSort.optimizedMergeSort(copy);
            long endTime = System.nanoTime();
            executionTimes[i] = (endTime - startTime) / 1000; // Convert to microseconds
        }
        Arrays.sort(executionTimes);
        return executionTimes[2]; // Median value
    }

    public static void main(String[] args) {
        // Generate and benchmark small, medium, and large arrays
        int[] smallArray = generateRandomArray(100);
        int[] mediumArray = generateRandomArray(1000);
        int[] largeArray = generateRandomArray(10000);

        long selectionSortTimeSmall = benchmarkSelectionSort(smallArray);
        long insertionSortTimeSmall = benchmarkInsertionSort(smallArray);
        long mergeSortTimeSmall = benchmarkMergeSort(smallArray);
        long optimizedMergeSortTimeSmall = benchmarkOptimizedMergeSort(smallArray);

        long selectionSortTimeMedium = benchmarkSelectionSort(mediumArray);
        long insertionSortTimeMedium = benchmarkInsertionSort(mediumArray);
        long mergeSortTimeMedium = benchmarkMergeSort(mediumArray);
        long optimizedMergeSortTimeMedium = benchmarkOptimizedMergeSort(mediumArray);

        long selectionSortTimeLarge = benchmarkSelectionSort(largeArray);
        long insertionSortTimeLarge = benchmarkInsertionSort(largeArray);
        long mergeSortTimeLarge = benchmarkMergeSort(largeArray);
        long optimizedMergeSortTimeLarge = benchmarkOptimizedMergeSort(largeArray);

        // Print results in microseconds
        System.out.println("Median Execution Times (in microseconds):");
        System.out.println("Small Array:");
        System.out.println("Selection Sort: " + selectionSortTimeSmall + " microseconds");
        System.out.println("Insertion Sort: " + insertionSortTimeSmall + " microseconds");
        System.out.println("Merge Sort: " + mergeSortTimeSmall + " microseconds");
        System.out.println("Optimized Merge Sort: " + optimizedMergeSortTimeSmall + " microseconds");

        System.out.println("\nMedium Array:");
        System.out.println("Selection Sort: " + selectionSortTimeMedium + " microseconds");
        System.out.println("Insertion Sort: " + insertionSortTimeMedium + " microseconds");
        System.out.println("Merge Sort: " + mergeSortTimeMedium + " microseconds");
        System.out.println("Optimized Merge Sort: " + optimizedMergeSortTimeMedium + " microseconds");

        System.out.println("\nLarge Array:");
        System.out.println("Selection Sort: " + selectionSortTimeLarge + " microseconds");
        System.out.println("Insertion Sort: " + insertionSortTimeLarge + " microseconds");
        System.out.println("Merge Sort: " + mergeSortTimeLarge + " microseconds");
        System.out.println("Optimized Merge Sort: " + optimizedMergeSortTimeLarge + " microseconds");
    }

    // Helper method to generate a random array of a given size
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * size);
        }
        return array;
    }
}
