import java.util.Arrays;
import java.util.Random;

public class LinkedListBenchmark {

    public static void benchmark(DoubleLinkedList doublyList, LinkedLinst singlyList, int n, int k, long[] resultsDoubly, long[] resultsSingly) {
        Random rand = new Random();
        int[] randomIndices = new int[k];

        for (int run = 0; run < 1000; run++) {
            // Reset for every run
            doublyList = new DoubleLinkedList();
            singlyList = new LinkedLinst();

            // Random indices
            for (int i = 0; i < k; i++) {
                randomIndices[i] = rand.nextInt(n);
            }

            DoubleLinkedList.Node[] doublyCellArray = new DoubleLinkedList.Node[n];
            for (int i = 0; i < n; i++) {
                doublyCellArray[i] = doublyList.new Node(i);
            }
            long startTimeDoubly = System.nanoTime();
            for (int i = 0; i < k; i++) {
                DoubleLinkedList.Node node = doublyCellArray[randomIndices[i]];
                doublyList.unlink(node);
                doublyList.insertFirst(node.item); // Insert the item, not the node
            }
            long endTimeDoubly = System.nanoTime();
            resultsDoubly[run] = endTimeDoubly - startTimeDoubly;

            LinkedLinst.Cell[] singlyCellArray = new LinkedLinst.Cell[n];
            for (int i = 0; i < n; i++) {
                singlyCellArray[i] = singlyList.new Cell(i, null);
                singlyList.add(singlyCellArray[i].head);
            }
            long startTimeSingly = System.nanoTime();
            for (int i = 0; i < k; i++) {
                LinkedLinst.Cell cell = singlyCellArray[randomIndices[i]];
                singlyList.unlink(cell);
                singlyList.add(cell.head);
            }
            long endTimeSingly = System.nanoTime();
            resultsSingly[run] = endTimeSingly - startTimeSingly;
        }
    }

    public static long median(long[] arr) {
        Arrays.sort(arr);
        int length = arr.length;
        if (length % 2 == 0) {
            return (arr[length / 2 - 1] + arr[length / 2]) / 2;
        } else {
            return arr[length / 2];
        }
    }

    public static void main(String[] args) {
        int n = 10000; // Sample size for the list
        int k = 1000;   // Number of random cells to unlink and insert

        DoubleLinkedList doublyList = new DoubleLinkedList();
        LinkedLinst singlyList = new LinkedLinst();

        long[] resultsDoubly = new long[1000];
        long[] resultsSingly = new long[1000];

        benchmark(doublyList, singlyList, n, k, resultsDoubly, resultsSingly);

        System.out.println("Median time taken for " + k + " unlink and insert operations in DoubleLinkedList: " + median(resultsDoubly) + " ns");
        System.out.println("Median time taken for " + k + " unlink and insert operations in LinkedLinst: " + median(resultsSingly) + " ns");
    }
}
