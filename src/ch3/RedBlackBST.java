package ch3;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color;
        int size;

        public Node(Key k, Value v, boolean c, int s) {
            this.key = k;
            this.val = v;
            this.color = c;
            this.size = s;
        }
    }

    private Node root;

    public RedBlackBST() {

    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private void resize(Node x) {
        x.size = size(x.left) + 1 + size(x.right);
    }

    private boolean isRed(Node x) {
        return x.color == RED;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int comp = key.compareTo(x.key);
            if (comp < 0) {
                x = x.left;
            } else if (comp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, RED, 1);
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = put(x.left, key, val);
        } else if (comp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }

        if (!isRed(x.left) && isRed(x.right)) {
            x.right = rotateLeft(x.right);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x.right = rotateRight(x.right);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        resize(x);

        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        x.left.color = RED;
        x.size = h.size;
        resize(h);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        x.right.color = RED;
        x.size = h.size;
        resize(h);
        return x;
    }

    private void flipColors(Node x) {
        x.color = RED;
        x.left.color = BLACK;
        x.right.color = BLACK;
    }

    public void delete(Key key) {

    }
}
