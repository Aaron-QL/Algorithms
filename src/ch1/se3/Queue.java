package ch1.se3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Queue<Item> implements Iterable<Item> {
    private class Node<Item> {
        Item item;
        Node<Item> next;
    }

    private Node<Item> first;
    private Node<Item> last;
    private int n;

    public Queue()
    {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public int size()
    {
        return n;
    }

    public void enqueue(Item item)
    {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        n++;
    }

    public Item dequeue()
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) {
            last = null;
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

    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                queue.enqueue(item);
            } else if (!queue.isEmpty()) {
                StdOut.print(queue.dequeue() + " ");
            }
        }
        StdOut.println("(" + queue.size() + " left on queue)");
        StdOut.println(queue.toString());
    }

    private class LinkedIterator implements Iterator<Item>
    {
        Node<Item> current;

        public LinkedIterator()
        {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }
}
