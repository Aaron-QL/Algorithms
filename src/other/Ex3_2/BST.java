package other.Ex3_2;

import other.Ex1_3.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        System.out.println(bst.floor("z"));
        bst.put("a", 1);
        bst.put("c", 2);
        bst.put("x", 3);
        bst.delete("x");
        bst.put("v", 4);
        bst.put("d", 5);
        bst.put("e", 1);
        bst.delete("e");
        bst.put("a", 2);
        bst.put("s", 3);
        for (String s : bst.keys()) {
            System.out.println(s + " : " + bst.get(s));
        }
    }

    private Node root;

    private class Node {
        Key key;
        Value val;
        int n;
        Node left;
        Node right;

        Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.n = 1;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.n;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp == 0) {
            return size(x.left) + 1;
        } else {
            return size(x.left) + 1 + rank(x.right, key);
        }
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return rank(root, key);
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t < k) {
            return select(x.right, k - t - 1);
        } else if (t > k) {
            return select(x.left, k);
        } else {
            return x;
        }
    }

    public Key select(int k) {
        if (k < 0 || k > size(root)) {
            throw new IllegalArgumentException();
        }
        return select(root, k).key;
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

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.n = size(x.left) + 1 + size(x.right);
        return x;
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

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);

        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
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

    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    private Node max(Node x) {
        return x.right == null ? x : max(x.right);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.left == null) {
                return x.left;
            }

            if (x.right == null) {
                return x.left;
            }
            Node t = x;
            x = min(x.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + 1 + size(x.right);
        return x;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        root = delete(root, key);
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        keys(q, root, lo, hi);
        return q;
    }

    private void keys(Queue<Key> q, Node x, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0) {
            keys(q, x.left, lo, hi);
        }

        if (cmpLo <= 0 && cmpHi >= 0) {
            q.enqueue(x.key);
        }

        if (cmpHi > 0) {
            keys(q, x.right, lo, hi);
        }
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Node t = floor(root, key);
        return t == null ? null : t.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return floor(x.left, key);
        } else if (cmp == 0) {
            return x;
        } else {
            Node t = floor(x.right, key);
            return t == null ? x : t;
        }
    }
}
