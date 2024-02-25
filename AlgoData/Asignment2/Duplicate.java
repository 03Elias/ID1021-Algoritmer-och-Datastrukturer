import java.util.Arrays;

class Duplicate {
    public static void main(String[] args) {
        int[] array1 = { 2, 3, 4, 6, 7, 10 };
        int[] array2 = { 3, 6, 8, 9, 10 };

        findDuplicates(array1, array2);
    }

    public static void findDuplicates(int[] arr1, int[] arr2) {
        Arrays.sort(arr2); // Sort the second array
        for (int i = 0; i < arr1.length; i++) {
            if (binarySearch(arr2, arr1[i])) {
                System.out.println("Duplicate found: " + arr1[i]);
            }
        }
    }

    public static boolean binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == key) {
                return true; // Key found
            }
            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false; // Key not found in the sorted array
    }
}
