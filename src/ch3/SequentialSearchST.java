package ch3;

import ch1.se3.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

@SuppressWarnings("unchecked")
public class SequentialSearchST<Key, Value>
{
    private int n = 0;
    private Node first;

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public SequentialSearchST() {

    }

    public int size() {
        return n;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public void put(Key key, Value val)
    {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (val == null) {
            delete(key);
        }
        for (Node i = first; i != null; i = i.next) {
            if (i.key.equals(key)) {
                i.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    public Value get(Key key)
    {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        for (Node i = first; i != null; i = i.next) {
            if (i.key.equals(key)) {
                return i.val;
            }
        }

        return null;
    }

    public void delete(Key key)
    {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        first = delete(first, key);
    }

    private Node delete(Node x, Key key)
    {
        if (x == null) {
            return null;
        }
        if (x.key.equals(key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys()
    {
        Queue q = new Queue();
        for (Node i = first; i != null; i = i.next) {
            q.enqueue(i.key);
        }

        return q;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }
}
