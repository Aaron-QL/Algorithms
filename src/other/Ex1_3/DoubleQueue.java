package other.Ex1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleQueue<Item> implements Iterable<Item> {

    public static void main(String[] args) {
        DoubleQueue<String> q = new DoubleQueue<>();
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        q.insert(3, "d");
        for (String s : q) {
            System.out.println(s);
        }


    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    Node first;
    Node last;
    int n = 0;

    boolean isEmpty()
    {
        return n == 0;
    }

    int size()
    {
        return n;
    }

    void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        n++;
    }

    Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        first.prev = null;
        if (n-- == 1) {
            last = null;
        }
        return item;
    }


    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {
        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    void deleteTail()
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (n-- == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
    }

    void delete(int k) {
        if (k < 1 || k > size()) {
            throw new NoSuchElementException();
        }

        if (n == 1) {
            first = null;
            last = null;
            n--;
            return;
        }

        if (k == 1) {
            first = first.next;
            first.prev = null;
            n--;
            return;
        } else if(k == size()) {
            last = last.prev;
            last.next = null;
            n--;
            return;
        }

        int i = 1;
        Node current = first;
        while (i++ < k) {
            current = current.next;
        }
        Node next = current.next;
        current.prev.next = next;
        next.prev = current.prev;
        n--;
        return;
    }

    boolean find(Node node, String key) {
        while (node != null) {
            if (node.item.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    void insert(int k, Item item) {
        if (k < 1 || k > size()) {
            throw  new NoSuchElementException();
        }
        if (k == 1) {
            enqueue(item);
        } else if (k == size()) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            oldLast.next = last;
            n++;
        } else {
            int i = 1;
            Node current = first;
            while (i++ < k) {
                current = current.next;
            }
            Node oldCurrent = current;
            current = new Node();
            current.item = item;
            current.next = oldCurrent;
            Node prev = oldCurrent.prev;
            oldCurrent.prev = current;
            prev.next = current;
            current.prev = prev;
            n++;
        }
    }
}
