public class DynamicQueue {
    private Integer[] array;
    private int size;
    private int capacity;
    private int front; // Index of the front element
    private int rear;  // Index of the rear element

    public DynamicQueue(int initialCapacity) {
        this.capacity = initialCapacity;
        this.array = new Integer[initialCapacity];
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
            // Double the capacity and copy elements to a new array
            int newCapacity = capacity * 2;
            Integer[] newArray = new Integer[newCapacity];

            // Copy elements from front to end (handling wrap-around)
            for (int i = 0; i < size; i++) {
                newArray[i] = array[(front + i) % capacity];
            }

            array = newArray;
            front = 0;
            rear = size - 1;
            capacity = newCapacity;
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

        // Shrink the array if necessary
        if (size < capacity / 4 && capacity > 4) {
            int newCapacity = capacity / 2;
            Integer[] newArray = new Integer[newCapacity];

            for (int i = 0; i < size; i++) {
                newArray[i] = array[(front + i) % capacity];
            }

            array = newArray;
            front = 0;
            rear = size - 1;
            capacity = newCapacity;
        }

        return removedItem;
    }

    public int size() {
        return size;
    }
}
