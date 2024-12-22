package data_structures;
public class BST {
    private class Node {
        String name;
        Node left, right;

        public Node(String name) {
            this.name = name;
            left = right = null;
        }
    }

    private Node root;

    public void insert(String name) {
        root = insertRec(root, name.toUpperCase()); //bst is case sensitive. so we convert the film name to UPPERCASE
    }

    private Node insertRec(Node root, String name) {
        
        if (root == null) {
            root = new Node(name);
            return root;
        }
        if (name.compareTo(root.name) < 0)
            root.left = insertRec(root.left, name);
        else if (name.compareTo(root.name) > 0)
            root.right = insertRec(root.right, name);
        return root;
    }

    public String search(String name) {
        return searchRec(root, name.toUpperCase());
    }

    private String searchRec(Node root, String name) {
        if (root == null || root.name.equals(name))
            return root != null ? root.name : null;
        if (name.compareTo(root.name) < 0)
            return searchRec(root.left, name);
        return searchRec(root.right, name);
    }

    public void delete(String name) {
        root = deleteRec(root, name.toUpperCase());
    }

    private Node deleteRec(Node root, String name) {
        if (root == null)
            return root;
        if (name.compareTo(root.name) < 0)
            root.left = deleteRec(root.left, name);
        else if (name.compareTo(root.name) > 0)
            root.right = deleteRec(root.right, name);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.name = minValue(root.right);
            root.right = deleteRec(root.right, root.name);
        }
        return root;
    }

    private String minValue(Node root) {
        String minv = root.name;
        while (root.left != null) {
            minv = root.left.name;
            root = root.left;
        }
        return minv;
    }
}

