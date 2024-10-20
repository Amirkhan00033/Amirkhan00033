package Node; 

class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    class Node {
        int data;
        Node left, right;
        boolean color;
    
        public Node(int data) {
            this.data = data;
            this.color = RED;
        }
    }
    
    private Node root;
    
    public RedBlackTree() {
        root = null;
    }
    
    public void insert(int data) {
        root = insert(root, data);
        root.color = BLACK;
    }
    
    private Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
    
        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        }
    
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
    
        return node;
    }
    
    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }
    
    private Node rotateLeft(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }
    
    private Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }
    
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    
    public void inorder() {
        inorder(root);
        System.out.println();
    }
    
    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print((node.color == RED ? "R" : "B") + "-" + node.data + " ");
            inorder(node.right);
        }
    }
    
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        int[] values = {10, 20, 30, 15, 25, 5, 1};
    
        for (int value : values) {
            rbt.insert(value);
            rbt.inorder();
        }
    }
    }