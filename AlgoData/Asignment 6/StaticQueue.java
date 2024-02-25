public class StaticQueue {
    private Integer[] array;
    private int size;
    private int capacity;
    private int front; // Index of the front element
    private int rear; // Index of the rear element

    public StaticQueue(int capacity) {
        this.capacity = capacity;
        this.array = new Integer[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void add(Integer item) {
        if (isFull()) {
            // Queue is full, handle it as you prefer (throw an exception or resize the
            // array)
            throw new IllegalStateException("Queue is full");
        }
        // Increment the rear index (with wrap-around)
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

    public Integer remove() {
        if (isEmpty()) {
            // Queue is empty, return null or throw an exception as desired
            return null;
        }

        Integer removedItem = array[front];
        array[front] = null; // Set the position to null
        front = (front + 1) % capacity;
        size--;
        return removedItem;
    }

    public int size() {
        return size;
    }
}
