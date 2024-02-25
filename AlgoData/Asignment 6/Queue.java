public class Queue {
    private Node head;
    private Node tail;

    private class Node {
        Integer item;
        Node next;

        private Node(Integer item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Queue() {
        head = null;
        tail = null;
    }

    public void add(Integer item) {
        Node newNode = new Node(item, null);

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Integer remove() {
        if (isEmpty()) {
            return null;
        }

        Integer removedItem = head.item;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        return removedItem;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
