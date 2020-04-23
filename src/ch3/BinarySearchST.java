package ch3;

import ch1.se3.Queue;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    public BinarySearchST() {
        keys = (Key[]) new Comparable[INIT_CAPACITY];
        vals = (Value[]) new Object[INIT_CAPACITY];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void resize(int capacity) {
        assert capacity >= n;
        Key[] tempKeys = (Key[]) new Comparable[capacity];
        Value[] tempVals = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempKeys[i] = keys[i];
            tempVals[i] = vals[i];
        }
        keys = tempKeys;
        vals = tempVals;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            delete(key);
        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }

        if (i == keys.length) {
            resize(keys.length * 2);
        }
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return vals[i];
        }
        return null;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int comp = keys[mid].compareTo(key);
            if (comp < 0) {
                lo = mid + 1;
            } else if (comp > 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return;
        }
        int i = rank(key);
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        n--;
        keys[n] = null;
        vals[n] = null;
        if (n > 6 && n < keys.length / 4) {
            resize(keys.length / 2);
        }
    }

    public Key select(int i)
    {
        if (i < 0 || i >= n) {
            throw new IllegalArgumentException();
        }
        return keys[i];
    }

    public Key min()
    {
        return keys[0];
    }

    public Key max()
    {
        return keys[n - 1];
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        Queue<Key> queue = new Queue<Key>();
        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
