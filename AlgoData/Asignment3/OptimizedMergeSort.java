public class OptimizedMergeSort {
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};

        System.out.println("Original Array:");
        printArray(array);

        optimizedMergeSort(array);

        System.out.println("\nSorted Array using Optimized Merge Sort:");
        printArray(array);
    }

    public static void optimizedMergeSort(int[] array) {
        if (array.length <= 1) {
            return; // Array with 0 or 1 element is already sorted
        }
        int[] aux = array.clone(); // Create a copy of the original array
        optimizedMergeSort(array, aux, 0, array.length - 1);
    }

    private static void optimizedMergeSort(int[] array, int[] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            // Recursively sort the two halves using the original and auxiliary arrays
            optimizedMergeSort(aux, array, lo, mid); // Toggle arguments
            optimizedMergeSort(aux, array, mid + 1, hi); // Toggle arguments

            // Merge the sorted halves into the auxiliary array
            optimizedMerge(array, aux, lo, mid, hi);
        }
    }

    private static void optimizedMerge(int[] array, int[] aux, int lo, int mid, int hi) {
        // Merge the two subarrays into the original array
        int i = lo; // Index for the left subarray
        int j = mid + 1; // Index for the right subarray

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > hi) {
                array[k] = aux[i++];
            } else if (aux[i] <= aux[j]) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
