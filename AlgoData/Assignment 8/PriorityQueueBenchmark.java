import java.util.Random;

// Priority Queue with O(1) Add and O(n) Remove
class PriorityQueueAddEfficient {
    private int[] arr;
    private int size;

    public PriorityQueueAddEfficient() {
        arr = new int[100000];
        size = 0;
    }

    public void add(int element) {
        arr[size++] = element;
    }

    public int remove() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }

        int minIndex = 0;
        for (int i = 1; i < size; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }

        int minValue = arr[minIndex];
        for (int i = minIndex; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;

        return minValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

// Priority Queue with O(n) Add and O(1) Remove
class PriorityRemoveEfficientQueue {
    private int[] arr;
    private int size;

    public PriorityRemoveEfficientQueue() {
        arr = new int[100000];
        size = 0;
    }

    public void add(int element) {
        if (size == 0) {
            arr[size++] = element;
        } else {
            int i;
            for (i = size - 1; i >= 0; i--) {
                if (element < arr[i]) {
                    arr[i + 1] = arr[i];
                } else {
                    break;
                }
            }
            arr[i + 1] = element;
            size++;
        }
    }

    public int remove() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty");
        }
        int minValue = arr[--size];
        return minValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

public class PriorityQueueBenchmark {
    public static void main(String[] args) {
        int numElements = 100000; // Number of elements to add and remove
        Random rand = new Random();

        // Benchmark the priority queue with O(1) Add and O(n) Remove
        PriorityQueueAddEfficient queue1 = new PriorityQueueAddEfficient();
        long startTime1 = System.nanoTime();

        for (int i = 0; i < numElements; i++) {
            int element = rand.nextInt(100);
            queue1.add(element);
        }

        long addTime1 = System.nanoTime() - startTime1;

        long startTime2 = System.nanoTime();
        while (!queue1.isEmpty()) {
            queue1.remove();
        }

        long removeTime1 = System.nanoTime() - startTime2;

        System.out.println("Priority Queue with O(1) Add and O(n) Remove:");
        System.out.println("Add Time: " + addTime1 + " ns");
        System.out.println("Remove Time: " + removeTime1 + " ns");
        System.out.println("Total Time: " + (addTime1 + removeTime1) + " ns");

        // Benchmark the priority queue with O(n) Add and O(1) Remove
        PriorityRemoveEfficientQueue queue2 = new PriorityRemoveEfficientQueue();
        long startTime3 = System.nanoTime();

        for (int i = 0; i < numElements; i++) {
            int element = rand.nextInt(100);
            queue2.add(element);
        }

        long addTime2 = System.nanoTime() - startTime3;

        long startTime4 = System.nanoTime();
        while (!queue2.isEmpty()) {
            queue2.remove();
        }

        long removeTime2 = System.nanoTime() - startTime4;

        System.out.println("\nPriority Queue with O(n) Add and O(1) Remove:");
        System.out.println("Add Time: " + addTime2 + " ns");
        System.out.println("Remove Time: " + removeTime2 + " ns");
        System.out.println("Total Time: " + (addTime2 + removeTime2) + " ns");
    }
}
