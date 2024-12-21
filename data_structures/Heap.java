package data_structures;

public class Heap<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> left, right;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public Heap() {
        this.root = null;
        this.size = 0;
    }

    // Add an element to the heap
    public void add(T data) {
        root = addRecursive(root, data);
        size++;
    }

    private Node<T> addRecursive(Node<T> current, T data) {
        if (current == null) {
            return new Node<>(data);
        }

        if (data.compareTo(current.value) < 0) {
            T temp = current.value;
            current.value = data;
            data = temp;
        }

        if (current.left == null) {
            current.left = addRecursive(current.left, data);
        } else if (current.right == null) {
            current.right = addRecursive(current.right, data);
        } else {
            current.left = addRecursive(current.left, data);
        }

        return current;
    }

    // Remove and return the root element (highest or lowest priority)
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T rootValue = root.value;
        root = removeRecursive(root);
        size--;
        return rootValue;
    }

    private Node<T> removeRecursive(Node<T> current) {
        if (current.left == null && current.right == null) {
            return null;
        }

        if (current.left != null && (current.right == null || current.left.value.compareTo(current.right.value) <= 0)) {
            current.value = current.left.value;
            current.left = removeRecursive(current.left);
        } else {
            current.value = current.right.value;
            current.right = removeRecursive(current.right);
        }

        return current;
    }

    // Peek at the root element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return root.value;
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the size of the heap
    public int size() {
        return size;
    }
    
}