package data_structures;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int length = 0;

    public LinkedList() {
        this.head = null;
    }

    public void add(T data) {
        Node temp = new Node(data);
        if (this.head == null) {
            head = temp;
        } else {
            Node x = head;
            while (x.next != null) {
                x = x.next;
            }
            x.next = temp;
        }
        length++;
    }

    public void remove(T key) {
        Node prev = null;
        Node temp = head;

        if (head != null && head.data.equals(key)) {
            head = head.next;
            length--;
            return;
        }

        while (temp != null && !temp.data.equals(key)) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            return;
        }

        prev.next = temp.next;
        length--;
    }

    public boolean contains(T key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(key)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // Clear the list
    public void clear() {
        head = null;
        length = 0;
    }

    public boolean empty() {
        return head == null;
    }

    public int length() {
        return this.length;
    }

    public T get(int index) {
        if (index < 0 || index >= length) {
            System.out.println("Index out of bounds");
        }

        Node temp = head;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return temp.data;
    }

    @Override
    public String toString() {
        // If the list is empty
        if (head == null) {
            return "Empty List";
        }

        StringBuilder s = new StringBuilder();
        Node x = head;
        while (x != null) {
            s.append(x.data).append(x.next != null ? " ---- " : "");
            x = x.next;
        }
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) {
                System.out.println("No such element");
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
