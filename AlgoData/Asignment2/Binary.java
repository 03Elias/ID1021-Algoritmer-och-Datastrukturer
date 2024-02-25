public class Binary {
    public static boolean search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;

        while (first <= last) {
            int index = first + (last - first) / 2;

            if (array[index] == key) {
                return true; // Key found
            } else if (array[index] < key) {
                first = index + 1; // Adjust the search range
            } else {
                last = index - 1; // Adjust the search range
            }
        }

        return false; // Key not found
    }

}
