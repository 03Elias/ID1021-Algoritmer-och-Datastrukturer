public class Linearopt {
    public static boolean search1(int[] array, int key) {
    
    for (int index = 0; index < array.length; index++) {
        if (array[index] == key) {
            return true; // Key found
        }
        if (array[index] > key) {
            return false; // Key is not in the array (no need to continue)
        }
    }
    return false; // Key not found in the sorted array

}
}



