package ch3;

import ch1.se3.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public BST() {

    }

    public boolean contain(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (x == null) {
            return null;
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return get(x.left, key);
        } else if (comp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
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
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = put(x.left, key, val);
        } else if (comp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return root == null ? null : min(root).key;
    }

    public Node min(Node x) {
        return x.left == null ? x : min(x.left);
    }

    public Key max() {
        return root == null ? null : max(root).key;
    }

    public Node max(Node x) {
        return x.right == null ? x : max(x.right);
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return floor(x.left, key);
        } else if (comp == 0) {
            return x;
        }
        Node t = floor(x.right, key);
        return t == null ? x : t;
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return rank(x.left, key);
        } else if (comp == 0) {
            return size(x.left);
        } else {
            return size(x.left) + 1 + rank(x.right, key);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = delete(x.left, key);
        } else if (comp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    private void print(Node x) {
        if (x == null) {
            return;
        }
        print(x.left);
        StdOut.println(x.key);
        print(x.right);
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public static void main(String[] args) {
        BST<String, Integer> st = new BST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.levelOrder()){
            StdOut.println(s + " " + st.get(s));
        }

        StdOut.println();

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<Key>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }
}
