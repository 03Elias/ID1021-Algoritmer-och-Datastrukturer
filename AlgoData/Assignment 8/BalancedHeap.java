public class BalancedHeap {
    private HeapNode root;

    public BalancedHeap() {
        root = null;
    }

    public void add(int item) {
        root = insert(root, item);
    }

    public int remove() {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = root.element;

        if (root.size == 1) {
            root = null;
        } else {
            root.size--;
            if (root.left == null) {
                promoteRight(root);
            } else if (root.right == null) {
                promoteLeft(root);
            } else if (root.right.element < root.left.element) {
                promoteRight(root);
            } else {
                promoteLeft(root);
            }
        }

        return min;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int findMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return root.element;
    }

    public int getSize() {
        return size(root);
    }

    public int push(Integer incr) {
        if (root == null) {
            throw new IllegalStateException("Heap is empty");
        }

        int currentDepth = 0; // Initialize depth counter
        root.element += incr; // Increment the root element

        // Push the element down the tree
        while (true) {
            if (root.left == null && root.right == null) {
                // If both branches are null, we've reached the bottom
                break;
            } else if (root.left != null && root.right != null) {
                // If both branches exist, choose the one with the smaller element
                if (root.left.element < root.right.element) {
                    swapWithLeftChild();
                    root = root.left;
                } else {
                    swapWithRightChild();
                    root = root.right;
                }
            } else if (root.left != null) {
                // If only the left branch exists, swap with it
                swapWithLeftChild();
                root = root.left;
            } else {
                // If only the right branch exists, swap with it
                swapWithRightChild();
                root = root.right;
            }

            currentDepth++; // Increment the depth counter
        }

        return currentDepth;
    }

    private HeapNode insert(HeapNode node, int item) {
        if (node == null) {
            return new HeapNode(item);
        }

        if (item < node.element) {
            node.left = insert(node.left, item);
        } else {
            node.right = insert(node.right, item);
        }

        node.size++; // Update the size of the subtree
        return balance(node);
    }

    private HeapNode deleteMin(HeapNode node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return balance(node);
    }

    private int size(HeapNode node) {
        return (node == null) ? 0 : node.size;
    }

    private HeapNode balance(HeapNode node) {
        if (node == null) {
            return null;
        }

        // Check if left subtree has more nodes
        if (size(node.left) > size(node.right) + 1) {
            // Perform a right rotation
            HeapNode newRoot = node.left;
            node.left = newRoot.right;
            newRoot.right = node;
            newRoot.size = node.size;
            node.size = 1 + size(node.left) + size(node.right);
            return newRoot;
        }

        // Check if right subtree has more nodes
        if (size(node.right) > size(node.left) + 1) {
            // Perform a left rotation
            HeapNode newRoot = node.right;
            node.right = newRoot.left;
            newRoot.left = node;
            newRoot.size = node.size;
            node.size = 1 + size(node.left) + size(node.right);
            return newRoot;
        }

        return node;
    }

    private void swapWithLeftChild() {
        int temp = root.element;
        root.element = root.left.element;
        root.left.element = temp;
    }

    private void swapWithRightChild() {
        int temp = root.element;
        root.element = root.right.element;
        root.right.element = temp;
    }

    private class HeapNode {
        int element;
        int size;
        HeapNode left;
        HeapNode right;

        HeapNode(int element) {
            this.element = element;
            this.size = 1;
            this.left = null;
            this.right = null;
        }
    }
    // Add these methods to the BalancedHeap class

private void promoteRight(HeapNode node) {
    int temp = node.element;
    node.element = node.right.element;
    node.right.element = temp;
}

private void promoteLeft(HeapNode node) {
    int temp = node.element;
    node.element = node.left.element;
    node.left.element = temp;
}

}
