import java.util.Random;
import java.util.Arrays;

class bench3 {

    private static void linear(int[] array, int[] indx, double[] times) {
        for (int i = 0; i < indx.length; i++) {
            Linear.search(array, indx[i]);
        }
        Arrays.sort(times);
    }

    private static int[] sorted(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(10);

        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 1;
        }
        Arrays.sort(array);
        return array;
    }

    private static int[] keys(int loop, int n) {
        Random rnd = new Random();
        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++) {
            indx[i] = rnd.nextInt(n * 5);
        }
        return indx;
    }

    public static void main(String[] arg) {

        int[] sizes = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600 };

        System.out.printf("# searching through an array of length n, time in ns\n");
        System.out.printf("#%7s%8s%8s\n", "n", "linear (sorted)", "linear (unsorted)");
        for (int n : sizes) {

            int loop = 10000;

            int[] sortedArray = sorted(n);
            int[] unsortedArray = sortedArray.clone(); // Clone the sorted array to make it unsorted
            shuffleArray(unsortedArray);

            int[] indx = keys(loop, n);

            System.out.printf("%8d", n);

            int k = 1000;

            double[] timesSorted = new double[k];
            double[] timesUnsorted = new double[k];

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                linear(sortedArray, indx, timesSorted);
                long t1 = System.nanoTime();
                timesSorted[i] = (t1 - t0);
            }

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                linear(unsortedArray, indx, timesUnsorted);
                long t1 = System.nanoTime();
                timesUnsorted[i] = (t1 - t0);
            }

            double medianSorted = timesSorted[k / 2];
            double medianUnsorted = timesUnsorted[k / 2];

            System.out.printf("%8.0f%8.0f\n", (medianSorted/1000), (medianUnsorted/1000));
        }
    }

    private static void shuffleArray(int[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
