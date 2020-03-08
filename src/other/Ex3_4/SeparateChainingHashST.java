package other.Ex3_4;

import other.Ex1_3.Queue;
import other.Ex3_1.SequentialSearchST;

public class SeparateChainingHashST<Key, Value> {
    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<>();
        st.put("a", 1);
        st.put("ab", 2);
        st.put("ac", 3);
        st.put("b", 4);
        st.put("bc", 5);
        st.put("c", 0);
        st.put("cc", 1);
        st.put("ba", 3);
        st.put("bb", 5);
        st.put("a", 2);
        st.put("ac", 4);
        st.put("bb", 6);
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }
    }
    private static final int INIT_CAPACITY = 5;
    private int n;
    private int m;
    private SequentialSearchST<Key, Value> st[];

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        return st[hash(key)].contains(key);
    }

    private void resize(int capacity) {
        SeparateChainingHashST<Key, Value> t = new SeparateChainingHashST<>(capacity);
        for (int i = 0; i < capacity; i++) {
            for (Key key : st[i].keys()) {
                t.put(key, st[i].get(key));
            }
        }
        this.n = t.n;
        this.m = t.m;
        this.st = t.st;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (val == null) {
            delete(key);
            return;
        }

        if (!contains(key)) {
            n++;
        }

        st[hash(key)].put(key, val);

        if (n >= m * 5) {
            resize(m * 2);
        }
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (st[hash(key)].contains(key)) {
            n--;
        }

        st[hash(key)].delete(key);

        if (n > INIT_CAPACITY && n <= m * 2) {
            resize(m / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < m; i++) {
            for (Key k : st[i].keys()) {
                q.enqueue(k);
            }
        }
        return q;
    }
}
