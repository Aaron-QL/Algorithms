package other.Ex3_4;

import other.Ex1_3.Queue;

public class LinearProbingHashST<Key, Value> {
    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        st.put("a", 1);
        st.put("a", 2);
        st.put("ac", 3);
        st.put("b", 4);
        st.put("bc", 6);
        st.put("c", 0);
        st.put("c", 1);
        st.put("ba", 3);
        st.put("bb", 5);
        st.put("a", 2);
        st.put("bb", 3);
        st.put("bb", -1);
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }

    private int m;
    private int n;
    private Key[] keys;
    private Value[] vals;
    private static final int INIT_CAPACITY = 7;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        n = 0;
        m = capacity;
        keys = (Key[]) new Object[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        m = t.m;
        keys = t.keys;
        vals = t.vals;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        return get(key) != null;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (val == null) {
            delete(key);
            return;
        }

        if (n >= m / 2) {
            resize(m * 2);
        }

        int i = hash(key);
        for (; keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }

        return null;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (!contains(key)) {
            return;
        }

        int i = hash(key);
        while (!keys[i].equals(key)) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        vals[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % m;
        }
        n--;

        if (n > 0 && n == m / 8) {
            resize(m / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                q.enqueue(keys[i]);
            }
        }
        return q;
    }
}
