package ch3;

import ch1.se3.Queue;
import ch1.se3.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value val;
        Node left;
        Node right;
        int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BST() {

    }


    private Node root;

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        return x == null ? 0 : x.size;
    }

    private void resize(Node x) {
        x.size = size(x.left) + 1 + size(x.right);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contain(Key key) {
        return get(key) != null;
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
        if (key == null) {
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException("calls put() with a null key");
        }
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        assert check();
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

        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Key min() {
        return root == null ? null : min(root).key;
    }

    private Node min(Node x) {
        return x.left == null ? x : min(x.left);
    }

    public Key max() {
        return root == null ? null : max(root).key;
    }

    private Node max(Node x) {
        return x.right == null ? x : max(x.right);
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
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return floor(x.left, key);
        } else if (comp == 0) {
            return x;
        } else {
            Node t = floor(x.right, key);
            return t == null ? x : t;
        }
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node t = ceiling(root, key);
        return t == null ? null : t.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comp = key.compareTo(x.key);
        if (comp > 0) {
            return ceiling(x.right, key);
        } else if (comp == 0) {
            return x;
        } else {
            Node t = ceiling(x.left, key);
            return t == null ? x : t;
        }
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
        } else if (t == k) {
            return x;
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
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            return rank(x.left, key);
        } else if (comp == 0) {
            return size(x.left) + 1;
        } else {
            return size(x.left) + 1 + rank(x.right, key);
        }
    }

    public void deleteMin() {
        if (isEmpty()) {
            return;
        }
        root = deleteMin(root);

        assert check();
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        resize(x);
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) {
            return;
        }
        root = deleteMax(root);
        assert check();
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        resize(x);
        return x;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        root = delete(root, key);

        assert check();
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
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node t = x;
            x = min(t.right);
            x.left = t.left;
            x.right = deleteMin(t.right);
        }
        resize(x);
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue queue = new Queue<Key>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        if (lo.compareTo(x.key) < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (lo.compareTo(x.key) <= 0 && hi.compareTo(x.key) >= 0) {
            queue.enqueue(x.key);
        }
        if (hi.compareTo(x.key) > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return 0;
        }

        return 1 + Math.max(height(x.left), height(x.right));
    }

    public static void main(String[] args) {
        BST<String, String> tree = new BST<>();
        tree.put("6", "1");
        tree.put("2", "2");
        tree.put("7", "3");
        tree.put("1", "4");
        tree.put("4", "5");
        tree.put("8", "6");
        tree.put("3", "7");
        tree.put("5", "8");
        tree.preOrderTraverse();
        tree.inOrderTraverse();
        tree.postOrderTraverse();
        tree.levelTraverse();
    }

    public void preOrderTraverse() {
        StdOut.printf("前序遍历：");
        preOrderTraverse(root);
        StdOut.println();
    }

    private void preOrderTraverse(Node x) {
        if (x == null) {
            return;
        }
        printNode(x);
        preOrderTraverse(x.left);
        preOrderTraverse(x.right);
    }

    private void preOrderTraverse2(Node x) {
        Stack<Node> s = new Stack<>();
        while (x != null || !s.isEmpty()) {
            if (x != null) {
                printNode(x);
                if (x.right != null) {
                    s.push(x.right);
                }
                x = x.left;
            } else {
                x = s.pop();
            }
        }
    }

    public void inOrderTraverse() {
        StdOut.printf("中序遍历：");
        inOrderTraverse(root);
        StdOut.println();
    }

    private void inOrderTraverse(Node x) {
        if (x == null) {
            return;
        }
        inOrderTraverse(x.left);
        printNode(x);
        inOrderTraverse(x.right);
    }

    private void inOrderTraverse2(Node x) {
        Stack<Node> s = new Stack<>();
        while (x != null || !s.isEmpty()) {
            if (x != null) {
                s.push(x);
                x = x.left;
            } else {
                x = s.pop();
                printNode(x);
                x = x.right;
            }
        }
    }

    public void postOrderTraverse() {
        StdOut.printf("后序遍历：");
        postOrderTraverse(root);
        StdOut.println();
    }

    private void postOrderTraverse(Node x) {
        if (x == null) {
            return;
        }
        postOrderTraverse(x.left);
        postOrderTraverse(x.right);
        printNode(x);
    }

    private void postOrderTraverse2(Node x) {
        Stack<Node> s = new Stack<>();
        while (x != null || !s.isEmpty()) {
            if (x != null) {
//                s.push(x);
//                x = x.left;
            } else {
//                x = s.pop();
//                printNode(x.right);
            }
        }
    }

    public void levelTraverse() {
        StdOut.printf("层次遍历：");
        levelTraverse(root);
        StdOut.println();
    }

    public void levelTraverse(Node x) {
        Queue<Node> q = new Queue();
        q.enqueue(x);
        while (!q.isEmpty()) {
            Node t = q.dequeue();
            printNode(t);
            if (t.left != null) {
                q.enqueue(t.left);
            }
            if (t.right != null) {
                q.enqueue(t.right);
            }
        }
    }

    private void printNode(Node x) {
        StdOut.printf("%s\t", x.val);
    }

    /*************************************************************************
     *  Check integrity of BST data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST()) StdOut.println("Not in symmetric order");
        if (!isSizeConsistent()) StdOut.println("Subtree counts not consistent");
        if (!isRankConsistent()) StdOut.println("Ranks not consistent");
        return isBST() && isSizeConsistent() && isRankConsistent();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) return true;
        if (x.size != size(x.left) + size(x.right) + 1) return false;
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0) return false;
        return true;
    }
}
