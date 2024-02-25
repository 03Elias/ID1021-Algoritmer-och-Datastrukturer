import java.util.Arrays;

public class pointer {

    public static void main(String[] args) {
        int[] array1 = { 2, 3, 4, 6, 7, 10 };
        int[] array2 = { 3, 6, 8, 9, 10 };

        int[] duplicates = findDuplicates(array1, array2);

        System.out.println("Common elements (duplicates): " + Arrays.toString(duplicates));
    }

    public static int[] findDuplicates(int[] arr1, int[] arr2) {
        // Sort both arrays
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int[] commonElements = new int[Math.min(arr1.length, arr2.length)];
        int commonIndex = 0;

        int index1 = 0;
        int index2 = 0;

        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] == arr2[index2]) {
                commonElements[commonIndex++] = arr1[index1];
                index1++;
                index2++;
            } else if (arr1[index1] < arr2[index2]) {
                index1++;
            } else {
                index2++;
            }
        }

        // Resize the commonElements array to the actual number of common elements found
        return Arrays.copyOf(commonElements, commonIndex);
    }
}
