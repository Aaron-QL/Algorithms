package Ex1_3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        s.push("q");
        s.push("w");
        s.push("e");
        for (String item : s) {
            System.out.println(item);
        }
    }

    private int n = 0;

    private Node first;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
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

    private class Node {
        Item item;
        Node next;
    }

    void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        first = first.next;
        n--;
        return oldFirst.item;
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }
}
