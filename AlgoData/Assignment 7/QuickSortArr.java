public class QuickSortArr {

    public static void sort(int[] arr, int min, int max) {
        if (min < max) {
            int pivotIndex = partition(arr, min, max);
            sort(arr, min, pivotIndex - 1);
            sort(arr, pivotIndex + 1, max);
        }
    }

    public static int partition(int[] arr, int min, int max) {
        int pivot = arr[min]; // Choose the first element as the pivot
        int i = min;
        int j = max;

        while (i < j) {
            while (i <= max && arr[i] <= pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot element with arr[j]
        int temp = arr[min];
        arr[min] = arr[j];
        arr[j] = temp;

        return j;
    }

    public static void main(String[] args) {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};

        System.out.println("Original Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        sort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
