import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<K extends Comparable<K>, V> implements Iterable<V> {

    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    private Node root;

    public BinaryTree() {
        root = null;
    }

    public void add(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            return;
        }

        Node current = root;
        while (true) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                if (current.left == null) {
                    current.left = new Node(key, value);
                    return;
                }
                current = current.left;
            } else if (cmp > 0) {
                if (current.right == null) {
                    current.right = new Node(key, value);
                    return;
                }
                current = current.right;
            } else {
                // Key already exists, update the value
                current.value = value;
                return;
            }
        }
    }

    public V lookup(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    @Override
    public Iterator<V> iterator() {
        return new TreeIterator();
    }

    public Iterator<V> depthFirstIterator() {
        return new DepthFirstIterator();
    }

    private class TreeIterator implements Iterator<V> {
        private Node next;
        private Stack<Node> stack;

        TreeIterator() {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException();
            }

            Node current = stack.pop();
            pushLeft(current.right); // Traverse the right subtree
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class DepthFirstIterator implements Iterator<V> {
        private Node next;
        private Stack<Node> stack;

        DepthFirstIterator() {
            stack = new Stack<>();
            pushLeft(root);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException();
            }

            Node current = stack.pop();
            pushLeft(current.right); // Traverse the right subtree
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    private class BreadthFirstIterator implements Iterator<V> {
    private Queue<Node> queue;

    BreadthFirstIterator() {
        queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root); // Enqueue the root node
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public V next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Node current = queue.poll(); // Dequeue the first node
        V value = current.value;

        if (current.left != null) {
            queue.offer(current.left); // Enqueue left child if not null
        }
        if (current.right != null) {
            queue.offer(current.right); // Enqueue right child if not null
        }

        return value;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}


    public static void main(String[] args) {
        BinaryTree<Integer, String> tree = new BinaryTree<>();

        // Adding key-value pairs to the tree
        tree.add(5, "Five");
        tree.add(2, "Two");
        tree.add(7, "Seven");
        tree.add(1, "One");
        tree.add(8, "Eight");
        tree.add(6, "Six");
        tree.add(3, "Three");

        // Lookup values by key
        String lookupResult = tree.lookup(2);
        System.out.println("Lookup result for key 2: " + lookupResult);

        // Iterating through the tree using the default iterator
        System.out.println("Iterating through the tree using default iterator:");
        for (String value : tree) {
            System.out.println("Next value: " + value);
        }

        // Iterating through the tree using the depth-first iterator
        System.out.println("Iterating through the tree using depth-first iterator:");
        Iterator<String> depthFirstIterator = tree.depthFirstIterator();
        while (depthFirstIterator.hasNext()) {
            System.out.println("Next value (Depth-first): " + depthFirstIterator.next());
        }
    }
}
