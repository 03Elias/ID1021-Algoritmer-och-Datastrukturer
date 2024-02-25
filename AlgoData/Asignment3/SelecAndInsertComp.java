import java.util.Random;
import java.util.Arrays;

public class  SelecAndInsertComp{
    public static void main(String[] args) {
        int[] arraySizes = {100, 1000, 10000, 100000};
        
        for (int size : arraySizes) {
            int[] arr1 = generateRandomArray(size);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length); // Create a copy for fair comparison
            
            long startTime1 = System.nanoTime();
            selectionSort(arr1);
            long endTime1 = System.nanoTime();
            
            long startTime2 = System.nanoTime();
            insertionSort(arr2);
            long endTime2 = System.nanoTime();
            
            long duration1 = (endTime1 - startTime1); 
            long duration2 = (endTime2 - startTime2); 
            
            System.out.println("Array size: " + size);
            System.out.println("Selection Sort Time: " + duration1 + " microseconds");
            System.out.println("Insertion Sort Time: " + duration2 + " microseconds");
            System.out.println();
        }
    }
    
    public static int[] generateRandomArray(int size) {
        Random rnd = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rnd.nextInt(1000); // Generate random integers
        }
        return array;
    }
    
    public static void selectionSort(int[] array) {
        // Implementation of selection sort (as previously provided)
    }
    
    public static void insertionSort(int[] array) {
        // Implementation of insertion sort (as previously provided)
    }
}
