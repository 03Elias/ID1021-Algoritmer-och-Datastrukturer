public class MinHeap {
    private int[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MinHeap() {
        heap = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    public void insert(int item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        bubbleUp(size - 1);
    }

    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        sinkDown(0);
        return min;
    }

    public int push(int incr) {
        heap[0] += incr;
        return sinkDown(0);
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            int[] newHeap = new int[2 * heap.length];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }
    }

    private void bubbleUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap[index] < heap[parent]) {
            swap(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private int sinkDown(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < size && heap[leftChild] < heap[smallest]) {
            smallest = leftChild;
        }
        if (rightChild < size && heap[rightChild] < heap[smallest]) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            return sinkDown(smallest);
        }
        return index;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();

        int[] elements = {5, 3, 8, 1, 6, 2};
        for (int elem : elements) {
            heap.insert(elem);
        }

        System.out.println("Heap: " + Arrays.toString(heap.heap));
        System.out.println("Extracted min element: " + heap.extractMin());
        System.out.println("Heap after extraction: " + Arrays.toString(heap.heap));
        System.out.println("Depth after push: " + heap.push(3));
        System.out.println("Heap after push: " + Arrays.toString(heap.heap));
    }
}
