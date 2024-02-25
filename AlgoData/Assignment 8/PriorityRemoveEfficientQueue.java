import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PriorityRemoveEfficientQueue {
    private ArrayList<Integer> elements = new ArrayList<>();

    public void add(int element) {
        elements.add(element); // O(n) add
        Collections.sort(elements); // Sort the list after each addition
    }

    public int remove() {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return elements.remove(0); // O(1) remove
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public static void main(String[] args) {
        PriorityRemoveEfficientQueue queue = new PriorityRemoveEfficientQueue();
        Random rand = new Random();

        // Add elements to the queue
        for (int i = 0; i < 10; i++) {
            int element = rand.nextInt(100);
            queue.add(element);
            System.out.println("Added: " + element);
        }

        // Remove elements from the queue
        while (!queue.isEmpty()) {
            int min = queue.remove();
            System.out.println("Removed: " + min);
        }
    }
}
