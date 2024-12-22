package data_structures;

public class Queue<T> {
    private Node<T> front; 
    private Node<T> rear; 
    private int size;     

    // Node class for linked list structure
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add an element to queue
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Remove and return the element at the front of the queue
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("The queue is empty");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return data;
    }

    // Peek at the element at the front without removing it
    public T peek() {
        if (isEmpty()) {
            System.out.println("The queue is empty");
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = front;
        while (current != null) {
            sb.append(current.data).append(" ");
            current = current.next;
        }
        return sb.toString();
    }
}