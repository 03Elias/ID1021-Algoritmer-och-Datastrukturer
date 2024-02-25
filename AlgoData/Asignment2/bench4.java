import java.util.Random;
import java.util.Arrays;

class bench4 {

    private static void linear(int[] array, int[] indx, double[] times) {
        for (int i = 0; i < indx.length; i++) {
            Linear.search(array, indx[i]);
        }
        Arrays.sort(times);
    }

    private static void binary(int[] array, int[] indx, double[] times) {
        for (int i = 0; i < indx.length; i++) {
            Binary.search(array, indx[i]);
        }
        Arrays.sort(times);
    }

    private static void pointer(int[] array1, int[] array2, double[] times) {
        pointer.findDuplicates(array1, array2);
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
        for (int j = 0; j < array.length - 1; j++) {
            if (array[j] > array[j + 1]) {
                int tempVar = array[j];
                array[j] = array[j + 1];
                array[j + 1] = tempVar;
            }
        }
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

        int[] sizes = { 200, 400, 800, 1600, 3200, 10000 };

        System.out.printf("# searching through an array of length n, time in ns\n");
        System.out.printf("#%7s%8s%8s%8s\n", "n", "linear", "binary", "pointer");
        for (int n : sizes) {

            int loop = 10000;

            int[] array = sorted(n);
            int[] indx = keys(loop, n);

            System.out.printf("%8d", n);

            int k = 1000;

            double[] timesLinear = new double[k];
            double[] timesBinary = new double[k];
            double[] timesPointer = new double[k];

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                linear(array, indx, timesLinear);
                long t1 = System.nanoTime();
                timesLinear[i] = (t1 - t0);
            }

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                binary(array, indx, timesBinary);
                long t1 = System.nanoTime();
                timesBinary[i] = (t1 - t0);
            }

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                pointer(array, indx, timesPointer);
                long t1 = System.nanoTime();
                timesPointer[i] = (t1 - t0);
            }

            double medianLinear = timesLinear[k / 2];
            double medianBinary = timesBinary[k / 2];
            double medianPointer = timesPointer[k / 2];

            System.out.printf("%8.0f%8.0f%8.0f\n", (medianLinear/1000), (medianBinary/1000), (medianPointer/1000));

        }
    }
}
