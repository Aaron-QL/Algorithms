package ch1.se3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class DoublyLinkedList<Item> implements Iterable<Item> {

    private class DoubleNode {
        Item item;
        DoubleNode previous;
        DoubleNode next;

        DoubleNode(Item item)
        {
            this.item = item;
        }

        DoubleNode(Item item, DoubleNode previous, DoubleNode next)
        {
            this.item = item;
            this.previous = previous;
            this.next = next;
            if (previous != null) {
                previous.next = this;
            }
            if (next != null) {
                next.previous = this;
            }
        }
    }

    DoubleNode first;
    DoubleNode last;
    int n;

    public DoublyLinkedList()
    {
        this.first = null;
        this.last = null;
        n = 0;
    }

    public int size()
    {
        return n;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public void insertAtTheBeginning(Item item)
    {
        if (isEmpty()) {
            first = new DoubleNode(item);
            last = first;
        } else {
            DoubleNode oldFirst = first;
            first = new DoubleNode(item, null, oldFirst);
        }
        n++;
    }

    public void insertAtTheEnd(Item item)
    {
        if (isEmpty()) {
            first = new DoubleNode(item);
            last = first;
        } else {
            last = new DoubleNode(item, last, null);
        }
        n++;
    }

    public void insertBeforeNode(Item beforeItem, Item item)
    {
        if (first.item.equals(beforeItem)) {
            insertAtTheBeginning(item);
            return;
        }
        DoubleNode current = first;
        while (current != null) {
            if (current.item.equals(beforeItem)) {
                new DoubleNode(item, current.previous, current);
                n++;
                return;
            }
            current = current.next;
        }
    }

    public void insertAfterNode(Item afterNode, Item item)
    {
        if (last.item.equals(item)) {
            insertAtTheEnd(item);
            return;
        }
        DoubleNode current = first;
        while (current != null) {
            if (current.item.equals(afterNode)) {
                new DoubleNode(item, current, current.next);
                n++;
                return;
            }
            current = current.next;
        }
        throw new NoSuchElementException();
    }

    public Item removeFromTheBeginning()
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        first.previous = null;
        if (size() == 1) {
            last = null;
        }
        n--;
        return item;
    }

    public Item removeFromTheEnd()
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.previous;
        last.next = null;
        if (size() == 1) {
            first = null;
        }
        n--;
        return item;
    }

    public Item removeItemWithIndex(int nodeIndex)
    {
        if (isEmpty() || nodeIndex <= 0 || nodeIndex > size()) {
            throw new NoSuchElementException();
        }
        if (nodeIndex == 1) {
            return removeFromTheBeginning();
        }
        if (nodeIndex == size()) {
            return removeFromTheEnd();
        }
        DoubleNode current = first;
        for (int i = 1; i < nodeIndex; i++) {
            current = current.next;
        }

        Item item = current.item;
        current.previous.next = current.next;
        current.next.previous = current.previous;
        n--;
        return item;
    }

    private class DoublyLinkedListIterator<Item> implements Iterator<Item>
    {
        DoubleNode current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = (Item) current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new DoublyLinkedListIterator<>();
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
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.insertAtTheBeginning(10);
        doublyLinkedList.insertAtTheBeginning(30);
        doublyLinkedList.insertAtTheEnd(999);
        StringJoiner doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("Doubly linked list items after initial insert: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 30 10 999");

        doublyLinkedList.insertBeforeNode(9999, 998);
        doublyLinkedList.insertBeforeNode(999, 997);
        doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("\nDoubly linked list items after insert before 999: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 30 10 997 999");

        doublyLinkedList.insertAfterNode(10, 11);

        doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("\nDoubly linked list items after insert after 10: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 30 10 11 997 999");

        doublyLinkedList.removeFromTheBeginning();
        doublyLinkedList.removeFromTheEnd();


        doublyLinkedList.removeItemWithIndex(2);

        doublyLinkedListItems = new StringJoiner(" ");
        for (int item : doublyLinkedList) {
            doublyLinkedListItems.add(String.valueOf(item));
        }

        StdOut.println("\nDoubly linked list items after deletions: " + doublyLinkedListItems.toString());
        StdOut.println("Expected: 10 997");
    }
}
