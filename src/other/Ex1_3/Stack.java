package other.Ex1_3;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        s.push("a");
        s.push("b");
        s.push("c");
        for (String item : s) {
            System.out.println(item);
        }
    }

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
        Node prev;
    }

    private Node first;
    private Node last;
    private int n;

    boolean isEmpty()
    {
        return n == 0;
    }

    int size() {
        return n;
    }

    void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        oldFirst.prev = first;
        n++;
    }

    Item pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node oldFirst = first;
        first = first.next;
        first.prev = null;
        n--;
        return oldFirst.item;
    }
}
