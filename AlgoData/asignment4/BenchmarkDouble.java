import java.util.Random;
import java.util.Arrays;

public class BenchmarkDouble {
    public static void main(String[] args) {
        int[] nValues = { 10000, 100000 }; // Vary the length of the lists
        int k = 1000; // Number of cells to unlink and insert
        Random random = new Random();
        int numIterations = 1000; // Number of benchmark iterations

        for (int n : nValues) {
            long[] singleLinkedListTimes = new long[numIterations];
            long[] doublyLinkedListTimes = new long[numIterations];

            for (int iteration = 0; iteration < numIterations; iteration++) {
                // Create a singly linked list and a doubly linked list
                LinkedList singleLinkedList = createSinglyLinkedList(n);
                DoublyLinkedList doublyLinkedList = createDoublyLinkedList(n);

                // Create an array of cells and references to them
                Cell[] cells = new Cell[n];
                for (int i = 0; i < n; i++) {
                    cells[i] = new Cell(i, null, null);
                }

                // Generate an array of k random indices
                int[] randomIndices = new int[k];
                for (int i = 0; i < k; i++) {
                    randomIndices[i] = random.nextInt(n);
                }

                // Benchmark unlink and insert operations for singly linked list
                long startTime = System.nanoTime();
                for (int index : randomIndices) {
                    Cell cell = cells[index];
                    singleLinkedList.unlink(cell);
                    singleLinkedList.insert(cell);
                }
                long endTime = System.nanoTime();
                singleLinkedListTimes[iteration] = (endTime - startTime);

                // Benchmark unlink and insert operations for doubly linked list
                startTime = System.nanoTime();
                for (int index : randomIndices) {
                    Cell cell = cells[index];
                    doublyLinkedList.unlink(cell);
                    doublyLinkedList.insert(cell);
                }
                endTime = System.nanoTime();
                doublyLinkedListTimes[iteration] = (endTime - startTime);
            }

            Arrays.sort(singleLinkedListTimes);
            Arrays.sort(doublyLinkedListTimes);

            long singleLinkedListMedian = singleLinkedListTimes[numIterations / 2];
            long doublyLinkedListMedian = doublyLinkedListTimes[numIterations / 2];

            System.out.println("Singly Linked List (n=" + n + ") Median: " + singleLinkedListMedian + " nanoseconds");
            System.out.println("Doubly Linked List (n=" + n + ") Median: " + doublyLinkedListMedian + " nanoseconds");
        }
    }

    // Create a singly linked list of length n
    private static LinkedList createSinglyLinkedList(int n) {
        LinkedList list = new LinkedList();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    // Create a doubly linked list of length n
    private static DoublyLinkedList createDoublyLinkedList(int n) {
        DoublyLinkedList list = new DoublyLinkedList();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    // Define Cell class (modify as needed)
    static class Cell {
        int value;
        Cell next;
        Cell prev;

        public Cell(int value, Cell next, Cell prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    // Define LinkedList class (modify as needed)
    static class LinkedList {
        Cell first;

        public void add(int item) {
            Cell newCell = new Cell(item, first, null);
            if (first != null) {
                first.prev = newCell;
            }
            first = newCell;
        }

        public void unlink(Cell cell) {
            if (first != null && cell != null) {
                // Find the previous cell before the cell to unlink
                Cell prev = cell.prev;
                if (prev != null) {
                    prev.next = cell.next;
                } else {
                    first = cell.next; // Update the first reference if the first cell is unlinked
                }
            }
        }

        public void insert(Cell cell) {
            if (first != null && cell != null) {
                cell.next = first;
                cell.prev = null;
                if (first != null) {
                    first.prev = cell;
                }
                first = cell;
            }
        }
    }

    // Define DoublyLinkedList class (modify as needed)
    static class DoublyLinkedList {
        Cell first;
        Cell last;

        public void add(int item) {
            Cell newCell = new Cell(item, null, last);
            if (last != null) {
                last.next = newCell;
            } else {
                first = newCell; // If the list is empty, set the new cell as the first
            }
            last = newCell;
        }

        public void insert(BenchmarkDouble.Cell cell) {
        }

        public void unlink(Cell cell) {
            if (cell != null) {
                // Unlink the cell by updating the previous and next references
                if (cell.prev != null) {
                    cell.prev.next = cell.next;
                } else {
                    first = cell.next; // Update the first reference if the first cell is unlinked
                }
            }
        }
    }
        }

       
