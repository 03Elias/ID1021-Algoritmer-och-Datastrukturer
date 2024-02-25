public class QueueTest {
    public static void main(String[] args) {
        testDynamicArrayQueue();
        testStaticArrayQueue();
        testLinkedListQueue();
    }

    public static void testDynamicArrayQueue() {
        System.out.println("Testing Dynamic Array Queue:");

        DynamicQueue dynamicQueue = new DynamicQueue(2);

        for (int i = 1; i <= 10; i++) {
            dynamicQueue.add(i);
            System.out.println("Added: " + i + ", Size: " + dynamicQueue.size());
        }

        while (!dynamicQueue.isEmpty()) {
            System.out.println("Removed: " + dynamicQueue.remove() + ", Size: " + dynamicQueue.size());
        }
    }

    public static void testStaticArrayQueue() {
        System.out.println("\nTesting Static Array Queue:");

        StaticQueue staticQueue = new StaticQueue(11);

        for (int i = 1; i <= 7; i++) {
            staticQueue.add(i);
            System.out.println("Added: " + i + ", Size: " + staticQueue.size());
        }

        while (!staticQueue.isEmpty()) {
            System.out.println("Removed: " + staticQueue.remove() + ", Size: " + staticQueue.size());
        }
    }

    public static void testLinkedListQueue() {
        System.out.println("\nTesting Linked List Queue:");

        Queue linkedListQueue = new Queue();

        for (int i = 1; i <= 5; i++) {
            linkedListQueue.add(i);
            System.out.println("Added: " + i);
        }

        while (!linkedListQueue.isEmpty()) {
            System.out.println("Removed: " + linkedListQueue.remove());
        }
    }
}
