package data_structures;

public class Stack<T> {
    private Node<T> top; // Top of the stack
    private int size;    // Size of the stack

    // Node class for linked list structure
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    // Constructor
    public Stack() {
        top = null;
        size = 0;
    }

    // Push an element onto the stack
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop an element from the stack
    public T pop() {
        if (isEmpty()) {
            System.out.println("The stack is empty");;
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // Peek at the top element without removing it
    public T peek() {
        if (isEmpty()) {
            System.out.println("The stack is empty");
        }
        return top.data;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Get the size of the stack
    public int size() {
        return size;
    }

    // Print the stack from the top to the nth element
    public void printStacktoN(int n) {
        if (isEmpty()) {
            System.out.println("The stack is empty");
            return;
        }
        if (n > size) {
            System.out.println("The stack has " + size + " elements");
            n = size;
        }
        
        Node<T> current = top;
        for (int i = 0; i < n; i++) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}