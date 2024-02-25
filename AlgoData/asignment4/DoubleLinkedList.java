public class DoubleLinkedList {
    public Node first;
    private Node last;
    private int size;

    public class Node {
        int item;
        Node next;
        Node prev;

        Node(int item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }
    }

    public void add(int item) {
        Node newNode = new Node(item);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public int length() {
        return size;
    }

    public boolean find(int item) {
        Node current = first;
        while (current != null) {
            if (current.item == item) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void remove(int item) {
        Node current = first;
        while (current != null) {
            if (current.item == item) {
                if (current == first) {
                    first = current.next;
                    if (first != null) {
                        first.prev = null;
                    }
                } else if (current == last) {
                    last = current.prev;
                    if (last != null) {
                        last.next = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return;
            }
            current = current.next;
        }
    }

    public void insertFirst(int item) {
        Node newNode = new Node(item);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        }
        size++;
    }

    public void unlink(Node node) {
        if (node == null) {
            return;
        }

        if (node == first) {
            first = node.next;
            if (first != null) {
                first.prev = null;
            }
        } else {
            if (node.prev != null) {
                node.prev.next = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            }

            if (node == last) {
                last = node.prev;
            }
        }
        size--;
    }

    public void display() {
        Node current = first;
        while (current != null) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.display();

        list.insertFirst(5);
        list.display();

        list.remove(20);
        list.display();

        list.unlink(list.first); // Unlink the first node
        list.display();

        System.out.println("Length: " + list.length());
        System.out.println("Find 30: " + list.find(30));
    }
}
