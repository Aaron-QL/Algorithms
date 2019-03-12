package Ex3_1;

import Ex1_3.Queue;

import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private final static int INIT_CAPACITY = 2;

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        st.put("a", 1);
        st.put("d", 2);
        st.put("e", 3);
        st.delete("c");
        st.put("a", 4);
        st.put("b", 5);
        st.deleteMax();
        st.put("b", 6);
        for (String s : st.keys()){
            System.out.println(s + " " + st.get(s));
        }
    }

    private Key[] keys;
    private Value[] vals;
    private int n = 0;

    BinarySearchST() {
        this(INIT_CAPACITY);
    }

    BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return vals[i];
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }

        if (val == null) {
            delete(key);
            return;
        }

        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            vals[i] = val;
            return;
        }

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
        if (++n == keys.length) {
            resize();
        }
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }

        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            for (int j = i; j < n; j++) {
                keys[j] = keys[j + 1];
                vals[j] = vals[j + 1];
            }
            if (--n <= keys.length / 3) {
                resize();
            }
        }
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) {
                lo = mid + 1;
            } else if (cmp < 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    private void resize() {
        int capacity = n * 2 + 1;
        Key[] tempKeys = (Key[]) new Comparable[capacity];
        Value[] tempVals = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempKeys[i] = keys[i];
            tempVals[i] = vals[i];
        }
        keys = tempKeys;
        vals = tempVals;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[n - 1];
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        return keys[k];
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        return i == 0 ? null : keys[i - 1];
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        int i = rank(key);

        return i == n ? null : keys[i];
    }


    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        delete(max());
    }

    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to size() is null");
        }

        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }
}