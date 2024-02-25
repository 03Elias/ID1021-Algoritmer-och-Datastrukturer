import java.util.Arrays;

public class LinkedListVsArrayBenchmark {

    public static void main(String[] args) {
        int[] sizesA = {1000, 5000, 10000, 20000}; // Vary the sizes for List A
        int[] sizesB = {1000, 5000, 10000, 20000}; // Vary the sizes for List B

        System.out.println("Scenario 1: Vary the size of List A and append to List B (List B is constant)");

        for (int sizeA : sizesA) {
            System.out.println("List A Size: " + sizeA);

            // Arrays to store individual measurements
            long[] linkedListTimesA = new long[100]; // Adjust the number of measurements
            long[] arrayTimesA = new long[100]; // Adjust the number of measurements

            for (int i = 0; i < 100; i++) { // Repeat the benchmark multiple times
                // Benchmark linked list append operation for List A and List B
                LinkedLinst linkedListA = generateLinkedList(sizeA);
                LinkedLinst linkedListB = generateLinkedList(1000); // List B is constant
                linkedListTimesA[i] = benchmarkLinkedListAppend(linkedListA, linkedListB); // Append List A to List B

                // Benchmark array append operation for List A and List B
                int[] arrayA = generateArray(sizeA);
                int[] arrayB = generateArray(1000); // List B is constant
                arrayTimesA[i] = benchmarkArrayAppend(arrayA, arrayB); // Append List A to List B
            }

            // Calculate and print median values
            long linkedListMedianTimeA = calculateMedian(linkedListTimesA);
            long arrayMedianTimeA = calculateMedian(arrayTimesA);

            System.out.println("Linked List Append Median Time (List A to List B): " + linkedListMedianTimeA + " nanoseconds");
            System.out.println("Array Append Median Time (List A to List B): " + arrayMedianTimeA + " nanoseconds");
            System.out.println(); // Add a separator between different sizes
        }

        System.out.println("Scenario 2: Keep the size of List B constant and vary the size of List A");

        for (int sizeB : sizesB) {
            System.out.println("List B Size: " + sizeB);

            // Arrays to store individual measurements
            long[] linkedListTimesB = new long[100]; // Adjust the number of measurements
            long[] arrayTimesB = new long[100]; // Adjust the number of measurements

            for (int i = 0; i < 100; i++) { // Repeat the benchmark multiple times
                // Benchmark linked list append operation for List A and List B
                LinkedLinst linkedListA = generateLinkedList(1000); // List A is constant
                LinkedLinst linkedListB = generateLinkedList(sizeB);
                linkedListTimesB[i] = benchmarkLinkedListAppend(linkedListA, linkedListB); // Append List B to List A

                // Benchmark array append operation for List A and List B
                int[] arrayA = generateArray(1000); // List A is constant
                int[] arrayB = generateArray(sizeB);
                arrayTimesB[i] = benchmarkArrayAppend(arrayA, arrayB); // Append List B to List A
            }

            // Calculate and print median values
            long linkedListMedianTimeB = calculateMedian(linkedListTimesB);
            long arrayMedianTimeB = calculateMedian(arrayTimesB);

            System.out.println("Linked List Append Median Time (List B to List A): " + linkedListMedianTimeB + " nanoseconds");
            System.out.println("Array Append Median Time (List B to List A): " + arrayMedianTimeB + " nanoseconds");
            System.out.println(); // Add a separator between different sizes
        }
    }


    // Rest of the code remains the same as in previous examples

    // Helper method to calculate the median from an array of values
    private static long calculateMedian(long[] values) {
        Arrays.sort(values);
        int middle = values.length / 2;
        if (values.length % 2 == 0) {
            // If the array length is even, average the two middle values
            return (values[middle - 1] + values[middle]) / 2;
        } else {
            // If the array length is odd, return the middle value
            return values[middle];
        }
    }

    // Other methods remain the same


    private static LinkedLinst generateLinkedList(int size) {
        LinkedLinst linkedList = new LinkedLinst();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        return linkedList;
    }
    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }
    private static long benchmarkLinkedListAppend(LinkedLinst listA, LinkedLinst listB) {
        long startTime = System.nanoTime();
        listA.append(listB);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    private static long benchmarkArrayAppend(int[] arrayA, int[] arrayB) {
        long startTime = System.nanoTime();
        int[] newArray = new int[arrayA.length + arrayB.length];
        System.arraycopy(arrayA, 0, newArray, 0, arrayA.length);
        System.arraycopy(arrayB, 0, newArray, arrayA.length, arrayB.length);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
    
    

