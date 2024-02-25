import java.util.ArrayList;
import java.util.Random;

public class PriorityAddEfficientQueue {
    private ArrayList<Integer> elements = new ArrayList<>();

    public void add(int element) {
        elements.add(element); // O(1) add
    }

    public int remove() {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        int minIndex = 0;
        for (int i = 1; i < elements.size(); i++) {
            if (elements.get(i) < elements.get(minIndex)) {
                minIndex = i;
            }
        }

        return elements.remove(minIndex); // O(n) remove
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public static void main(String[] args) {
        PriorityAddEfficientQueue queue = new PriorityAddEfficientQueue();
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
