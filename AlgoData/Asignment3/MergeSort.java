public class MergeSort {
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};

        System.out.println("Original Array:");
        printArray(array);

        mergeSort(array);

        System.out.println("\nSorted Array using Merge Sort:");
        printArray(array);
    }

    public static void mergeSort(int[] array) {
        if (array.length <= 1) {
            return; // Array with 0 or 1 element is already sorted
        }
        int[] aux = new int[array.length];
        mergeSort(array, aux, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            // Recursively sort the two halves
            mergeSort(array, aux, lo, mid);
            mergeSort(array, aux, mid + 1, hi);

            // Merge the sorted halves
            merge(array, aux, lo, mid, hi);
        }
    }

    private static void merge(int[] array, int[] aux, int lo, int mid, int hi) {
        // Copy data to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        int i = lo; // Index for the left subarray
        int j = mid + 1; // Index for the right subarray

        // Merge the two subarrays into the original array
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
