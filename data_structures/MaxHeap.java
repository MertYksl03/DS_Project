package data_structures;

public class MaxHeap {

    private final int maxSize;
    private final Node[] heap;
    private int currentSize;

    private class Node {
        public String name;
        public double data1;
        public int data2;
    
        // Default constructor
        public Node() {
            name = null;
            data1 = 0;
            data2 = 0;
        }
    
        // Constructor overload
        public Node(String name, double data1, int data2) {
            this.name = name;
            this.data1 = data1;
            this.data2 = data2;
        }
    
        @Override
        public String toString() {
            return name + " : " + data1 + " : " + data2 + " ";
        }
    }
    

    public MaxHeap(int maxSize) {
        this.heap = new Node[maxSize];
        this.currentSize = -1;
        this.maxSize = maxSize;
    }

    // Returns the index of the parent node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Returns the index of the left child 
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    // Returns the index of the right child 
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    // Inserts the item at the appropriate position
    public void insert(String name, double data1, int data2) {
        if (currentSize == maxSize - 1) {
            System.out.println("The heap is full. Cannot insert");
            return;
        }
    
        // Create a new node object
        Node node = new Node(name, data1, data2);
    
        // Insert the item at the last position of the array and move it up
        currentSize++;
        this.heap[currentSize] = node;
    
        // Move up until the heap property is satisfied
        int currentIndex = currentSize;
        while (currentIndex > 0) {
            int parentIndex = this.parent(currentIndex);
    
            Node currentNode = heap[currentIndex];
            Node parentNode = heap[parentIndex];
    
            // Compare by `data1`, then `data2`
            if (parentNode.data1 < currentNode.data1 || 
               (parentNode.data1 == currentNode.data1 && parentNode.data2 < currentNode.data2)) {
                // Swap nodes
                heap[currentIndex] = parentNode;
                heap[parentIndex] = currentNode;
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }
    

    public void insert (Node node) {
        if (currentSize == maxSize - 1) {
            System.out.println("The heap is full. Cannot insert");
            return;
        }

        // Insert the item at the last position of the array 
        // and move it up
        currentSize++;
        this.heap[currentSize] = node;
        Node tempNode;
        
        // Move up until the heap property is satisfied
        int currentIndex = currentSize;
        
        while( currentIndex > 0 && this.heap[this.parent(currentIndex)].data1 < this.heap[currentIndex].data1) {
            tempNode = heap[currentIndex];
            heap[currentIndex] = heap[parent(currentIndex)];
            heap[parent(currentIndex)] = tempNode;
            currentIndex = this.parent(currentIndex);
        }
    }

    // Moves the item at position i into its appropriate position
    private void maxHeapify(int i) {
        int left = this.leftChild(i);
        int right = this.rightChild(i);
        int largest = i;
    
        // Check if left child is within bounds and compare
        if (left < currentSize && 
           (heap[left].data1 > heap[largest].data1 || 
           (heap[left].data1 == heap[largest].data1 && heap[left].data2 > heap[largest].data2))) {
            largest = left;
        }
    
        // Check if right child is within bounds and compare
        if (right < currentSize && 
           (heap[right].data1 > heap[largest].data1 || 
           (heap[right].data1 == heap[largest].data1 && heap[right].data2 > heap[largest].data2))) {
            largest = right;
        }
    
        // If the largest is not the current node, swap and continue
        if (largest != i) {
            Node temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;
            maxHeapify(largest); // Recursively fix the affected subtree
        }
    }
    
    

    // Returns the maximum item of the heap
    public Node getMax() {
        if (currentSize == -1) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    // Deletes the maximum item and returns it
    public Node extractMax() {
        if (currentSize == -1) {
            throw new IllegalStateException("Heap is empty");
        }

        Node maxNode = new Node();
        maxNode = this.heap[0];

        // Replace the first item with the last item
        this.heap[0] = this.heap[currentSize];
        currentSize--;

        // Maintain the heap property by heapifying the first item
        maxHeapify(0);
        return maxNode;
    }

    public void delete(String name) {
        int i;
        for (i = 0; i <= currentSize; i++) {
            if (heap[i].name.equals(name)) {
                break;
            }
        }

        if (i == currentSize + 1) {
            System.out.println("The node with name " + name + " is not found");
            return;
        }

        // Replace the node with the last node
        heap[i] = heap[currentSize];
        currentSize--;

        // Maintain the heap property by heapifying the node
        maxHeapify(i);        
    }

    public boolean isEmpty() {
        return currentSize == -1;
    }

    public boolean isFull() {
        return currentSize == maxSize - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= currentSize; i++) {
            sb.append(heap[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
