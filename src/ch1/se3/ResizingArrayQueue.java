package ch1.se3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        q.enqueue("a");
        q.enqueue("b");
        StdOut.println(q.dequeue());
        q.enqueue("c");
        q.enqueue("d");
        q.enqueue("e");
        StdOut.println(q.dequeue());
        q.enqueue("f");
        q.enqueue("g");
        q.enqueue("h");
        StdOut.println(q.dequeue());

        for (String s : q) {
            StdOut.println(s);
        }

        StdOut.println(q.toString());
    }

    private Item[] a = (Item[]) new Object[1];

    private int first = 0;
    private int last = 0;

    public boolean isEmpty()
    {
        return last - first == 0;
    }

    public int size()
    {
        return last - first;
    }

    public void resize()
    {
        int capacity = size() * 2;
        Item[] temp = (Item[]) new Object[capacity];
        int index = 0;
        for (int i = first; i < last; i++) {
            temp[index++] = a[i];
        }
        a = temp;
        first = 0;
        last = index;
    }

    public void enqueue(Item item)
    {
        a[last++] = item;
        if (last == a.length) {
            resize();
        }
    }

    public Item dequeue()
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = a[first];
        a[first++] = null;
        if (first > (a.length / 2)) {
            resize();
        }
        return item;
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    private class ArrayIterator implements Iterator<Item>
    {
        int current = first;

        @Override
        public boolean hasNext() {
            return current != last;
        }

        @Override
        public Item next() {
            return a[current++];
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }
}
