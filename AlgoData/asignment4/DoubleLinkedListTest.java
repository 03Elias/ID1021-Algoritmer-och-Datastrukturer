public class DoubleLinkedListTest {
    public static void main(String[] args) {
        // Create a new DoubleLinkedList
        DoubleLinkedList list = new DoubleLinkedList();

        // Add elements to the list
        list.add(10);
        list.add(20);
        list.add(30);

        // Display the initial list
        System.out.print("Initial List: ");
        list.display(); // Output: 30 20 10

        // Insert an element at the beginning
        list.insertFirst(5);

        // Display the updated list
        System.out.print("List after insert: ");
        list.display(); // Output: 5 30 20 10

        // Check if an element exists in the list
        int itemToFind = 20;
        boolean found = list.find(itemToFind);
        System.out.println("Find " + itemToFind + ": " + found); // Output: Find 20: true

        // Remove an element from the list
        int itemToRemove = 20;
        list.remove(itemToRemove);

        // Display the updated list after removal
        System.out.print("List after remove: ");
        list.display(); // Output: 5 30 10

        // Unlink the first node
        list.unlink(list.first);

        // Display the list after unlinking the first node
        System.out.print("List after unlink: ");
        list.display(); // Output: 30 10

        // Get the length of the list
        int length = list.length();
        System.out.println("Length: " + length); // Output: Length: 2
    }
}
