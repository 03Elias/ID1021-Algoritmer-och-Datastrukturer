import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    static class QuickSortLinkedList {
        private Node head;
        private Node tail;

        public void append(int value) {
            Node newNode = new Node(value);
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        public void quickSort() {
            head = quickSortRec(head);
        }

        private Node quickSortRec(Node node) {
            if (node == null || node.next == null) {
                return node;
            }

            Node pivot = node;
            Node smallerHead = null;
            Node smallerTail = null;
            Node largerHead = null;
            Node largerTail = null;

            Node current = node.next;

            while (current != null) {
                if (current.value <= pivot.value) {
                    if (smallerHead == null) {
                        smallerHead = current;
                        smallerTail = current;
                    } else {
                        smallerTail.next = current;
                        smallerTail = current;
                    }
                } else {
                    if (largerHead == null) {
                        largerHead = current;
                        largerTail = current;
                    } else {
                        largerTail.next = current;
                        largerTail = current;
                    }
                }
                current = current.next;
            }

            if (smallerTail != null) {
                smallerTail.next = null;
            }
            if (largerTail != null) {
                largerTail.next = null;
            }

            smallerHead = quickSortRec(smallerHead);
            largerHead = quickSortRec(largerHead);

            if (smallerHead == null) {
                pivot.next = largerHead;
                return pivot;
            }

            Node tailSmaller = smallerHead;
            while (tailSmaller.next != null) {
                tailSmaller = tailSmaller.next;
            }

            tailSmaller.next = pivot;
            pivot.next = largerHead;

            return smallerHead;
        }

        public void display() {
            Node current = head;
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.next;
            }
            System.out.println();
        }

        // Convert the linked list to an array
        public int[] toArray() {
            Node current = head;
            int size = 0;

            while (current != null) {
                size++;
                current = current.next;
            }

            int[] array = new int[size];
            current = head;

            for (int i = 0; i < size; i++) {
                array[i] = current.value;
                current = current.next;
            }

            return array;
        }

        // Clone the linked list
        public QuickSortLinkedList clone() {
            QuickSortLinkedList newList = new QuickSortLinkedList();
            Node current = head;

            while (current != null) {
                newList.append(current.value);
                current = current.next;
            }

            return newList;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int numLoops = 10000;
        int[] inputSizes = {100, 500, 1000, 5000, 10000};

        for (int size : inputSizes) {
            long[] linkedListTimes = new long[numLoops];
            long[] arrayTimes = new long[numLoops];

            for (int i = 0; i < numLoops; i++) {
                QuickSortLinkedList list = new QuickSortLinkedList();
                int[] array = new int[size];

                for (int j = 0; j < size; j++) {
                    int value = rand.nextInt(10000); // Generate random values
                    list.append(value);
                    array[j] = value;
                }

                // Clone the linked list
                QuickSortLinkedList clonedList = new QuickSortLinkedList();
                Node current = list.head;
                while (current != null) {
                    clonedList.append(current.value);
                    current = current.next;
                }

                int[] clonedArray = Arrays.copyOf(array, array.length); // Clone the array

                // Measure and record time for linked list sorting
                long startTime = System.nanoTime();
                clonedList.quickSort();
                long endTime = System.nanoTime();
                linkedListTimes[i] = endTime - startTime;

                // Measure and record time for array sorting
                startTime = System.nanoTime();
                Arrays.sort(clonedArray);
                endTime = System.nanoTime();
                arrayTimes[i] = endTime - startTime;
            }

            Arrays.sort(linkedListTimes);
            Arrays.sort(arrayTimes);

            long medianLinkedListTime = linkedListTimes[numLoops / 2];
            long medianArrayTime = arrayTimes[numLoops / 2];

            System.out.println("Input Size: " + size);
            System.out.println("Median Time for Linked List Sort: " + medianLinkedListTime + " nanoseconds");
            System.out.println("Median Time for Array Sort: " + medianArrayTime + " nanoseconds");
            System.out.println();
        }
    }
}
