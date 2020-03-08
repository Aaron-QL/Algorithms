package other.Ex3_3;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {

    }
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        Key key;
        Value val;
        int n;
        boolean color;
        Node left, right;

        Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }

        return size(x.left) + 1 + size(x.right);
    }

    public int size() {
        return size(root);
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h);
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(h.key);
        if (cmp > 0) {
            return h.right = put(h.right, key, val);
        } else if (cmp < 0) {
            return h.left = put(h.left, key, val);
        } else {
            h.val = val;
        }

        if (!isRed(h.left) && isRed(h.right)) {
            h = rotateLeft(h);
        }

        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        h.n = size(h);
        return h;
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

    private Node balance(Node x) {
        assert x != null;

        if (isRed(x.right)) {
            x = rotateLeft(x);
        }

        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }

        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        x.n = size(x);
        return x;
    }



    public void delete(Key key) {

    }

    public boolean isEmpty() {
        return root == null;
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(root, key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }



}
