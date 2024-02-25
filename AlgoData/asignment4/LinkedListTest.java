public class LinkedListTest {
    public static void main(String[] args) {
        LinkedLinst linkedList = new LinkedLinst();

        // Add elements to the list
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);

        // Print the length of the list
        System.out.println("Length of the list: " + linkedList.length());

        // Find elements in the list
        System.out.println("Found 20: " + linkedList.find(20)); // Should print true
        System.out.println("Found 40: " + linkedList.find(40)); // Should print false

        // Remove an element from the list
        linkedList.remove(20);
        System.out.println("Length after removing 20: " + linkedList.length());

        // Insert an element at the first position
        linkedList.insertFirst(5);
        System.out.println("Length after inserting 5 at the beginning: " + linkedList.length());

        // Unlink a cell
        linkedList.unlink(linkedList.first);
        System.out.println("Length after unlinking the first cell: " + linkedList.length());

        // Create another linked list for appending
        LinkedLinst otherList = new LinkedLinst();
        otherList.add(100);
        otherList.add(200);

        // Append the other list to the original list
        linkedList.append(otherList);
        System.out.println("Length after appending the other list: " + linkedList.length());
    }
}
