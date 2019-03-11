package Ex3_1;

import Ex1_3.Queue;

import java.util.Iterator;

public class SequentialSearchST<Key, Value> {
    private Node first;
    private int n = 0;

    private class Node {
        Key key;
        Value val;
        Node next;

        Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node i = first; i != null; i = i.next) {
            if (key.equals(i.key)) {
                return i.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node i = first; i != null; i = i.next) {
            if (key.equals(i.key)) {
                i.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    public int size() {
        return n;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        for (Node i = first; i != null; i = i.next) {
            q.enqueue(i.key);
        }
        return q;
    }

    private class STIterator implements Iterator<Key> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }
    }

    public void delete(Key key) {
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        st.put("a", 1);
        st.put("b", 2);
        st.put("d", 3);
        st.put("e", 4);
        st.put("c", 6);
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }

    }
}
