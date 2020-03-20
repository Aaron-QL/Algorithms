package ch1.se3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class CircleQueue<Item> implements Iterable<Item> {
    private class Node<Item> {
        Item item;
        Node<Item> next;

        Node(Item item) {
            this.item = item;
        }

        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<Item> last;
    private int n;

    public CircleQueue() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            last = new Node<>(item);
        } else if (size() == 1) {
            last.next = new Node<>(item, last);
            last = last.next;
        } else {
            Node oldLast = last;
            last = new Node<>(item, oldLast.next);
            oldLast.next = last;
        }


        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item t = last.next.item;
        last.next = last.next.next;
        n--;
        return t;
    }

    public static void main(String[] args) {
        CircleQueue<Integer> queue = new CircleQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        StringJoiner queueItems = new StringJoiner(" ");
        for (int item : queue) {
            queueItems.add(String.valueOf(item));
        }

        StdOut.println("Queue items: " + queueItems.toString());
        StdOut.println("Expected: 1 2 3 4");
    }

    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Node current;
        int count = 0;

        public QueueIterator() {
            if (last != null && size() > 1) {
                current = last.next;
            } else {
                current = last;
            }
        }

        public Item next() {
            count++;

            Item item = (Item) current.item;
            current = current.next;
            return item;
        }

        public boolean hasNext() {
            return count < size();
        }
    }
}
