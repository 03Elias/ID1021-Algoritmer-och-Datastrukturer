class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}

public class QuickSortLinkedList {
    Node head;
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

public static void main(String[] args) {
        QuickSortLinkedList list = new QuickSortLinkedList();
        list.append(7);
        list.append(2);
        list.append(1);
        list.append(6);
        list.append(8);
        list.append(5);
        list.append(3);
        list.append(4);

        System.out.println("Original Linked List:");
        list.display();

        list.quickSort();

        System.out.println("Sorted Linked List:");
        list.display();
    }
}
