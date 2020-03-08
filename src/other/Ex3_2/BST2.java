package other.Ex3_2;

import other.Ex1_3.Queue;

public class BST2<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {
        BST2<String, Integer> bst = new BST2<>();
        bst.put("a", 1);
        bst.put("d", 2);
        bst.put("d", 1);
        bst.put("e", 3);
        bst.put("a", 2);
        bst.put("w", 4);
        bst.put("x", 5);

    }

    Node root;

    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int n;

        Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
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
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else {
            x.val = val;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = delete(x.right, key);
        } else if (cmp < 0) {
            x.left = delete(x.left, key);
        } else {
            if (x.left == null) {
                return x.right;
            }

            if (x.right == null) {
                return x.left;
            }

            Node t = x;
            x = min(x.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.n;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    private Node max(Node x) {
        return x.right == null ? x : max(x.right);
    }

    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    private Node min(Node x) {
        return x.left == null ? x : min(x.left);
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

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, key);
        }

        if (cmp == 0) {
            return x;
        }

        Node t = floor(x.right, key);
        return t != null ? t : x;
    }

    public Key select(int k) {
        if (k < 0 || k > root.n) {
            throw new IllegalArgumentException();
        }

        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }

        int t = size(x.left);

        if (t == k) {
            return x;
        } else if (t > k) {
            return select(x.left, k);
        } else {
            return select(x.right, k - t - 1);
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
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return size(x.left) + 1 + rank(x.right, key);
        } else {
            return size(x.left) + 1;
        }
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        keys(root, q, lo, hi);
        return q;
    }

    private void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        if (x == null) {
            return;
        }

        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0) {
            keys(x.left, q, lo, hi);
        }

        if (cmpLo <= 0 && cmpHi >= 0) {
            q.enqueue(x.key);
        }

        if (cmpHi > 0) {
            keys(x.right, q, lo, hi);
        }
    }
}
