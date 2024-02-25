import java.util.Random;
import java.util.Arrays;

class Bench {

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

        int[] sizes = { 200, 400, 800, 1600, 3200 };

        System.out.printf("# searching through an array of length n, time in ns\n");
        System.out.printf("#%7s%8s%8s\n", "n", "linear", "binary");
        for (int n : sizes) {

            int loop = 10000;

            int[] array = sorted(n);
            int[] indx = keys(loop, n);

            System.out.printf("%8d", n);

            int k = 1000;

            double[] timesLinear = new double[k];
            double[] timesBinary = new double[k];

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                linear(array, indx, timesLinear);
                long t1 = System.nanoTime();
                timesLinear[i] = (t1 - t0);
            }

            double medianLinear = timesLinear[k / 2];

            System.out.printf("%8.0f", (medianLinear/1000));

            for (int i = 0; i < k; i++) {
                long t0 = System.nanoTime();
                binary(array, indx, timesBinary);
                long t1 = System.nanoTime();
                timesBinary[i] = (t1 - t0);
            }

            double medianBinary = timesBinary[k / 2];

            System.out.printf("%8.0f\n", (medianBinary/1000));
        }
    }
}
