package ch1.se3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class List<Item> implements Iterable<Item> {
    private class Node<Item> {
        Item item;
        Node<Item> next;

        Node(Item item)
        {
            this.item = item;
        }

        Node(Item item, Node next)
        {
            this.item = item;
            this.next = next;
        }
    }

    private Node<Item> first;
    private int n;

    public List()
    {
        first = null;
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

    public void add(Item item)
    {
        if (isEmpty()) {
            first = new Node<>(item);
        } else {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            Node newNode = new Node(item);
            current.next = newNode;
        }
        n++;
    }

    public void deleteLastNode()
    {
        if (isEmpty()) {
            return;
        }
        if (size() == 1) {
            first = null;
        } else {
            Node current = first;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        n--;
    }

    public void delete(int k)
    {
        if (isEmpty() || k <= 0 || k > size()) {
            return;
        }
        if (k == 1) {
            first = first.next;
        } else {
            Node current = first;
            for (int i = 0; i < k - 2; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        n--;
    }

    public boolean find(Item key)
    {
        Node current = first;
        while (current != null) {
            if (current.item.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void removeAfter(Item item)
    {
        if (isEmpty() || item == null) {
            return;
        }
        Node current = first;
        while (current != null) {
            if (current.item.equals(item)) {
                if (current.next != null) {
                    current.next = current.next.next;
                    n--;
                }
                return;
            }
            current = current.next;
        }
    }

    public void insertAfter(Item firstItem, Item secondItem)
    {
        if (isEmpty() || firstItem == null || secondItem == null) {
            return;
        }
        Node current = first;
        while (current != null) {
            if (current.item.equals(firstItem)) {
                Node t = new Node(secondItem, current.next);
                current.next = t;
                n++;
                return;
            }
            current = current.next;
        }
    }

    public void remove(Item key)
    {
        if (isEmpty() || key == null) {
            return;
        }
        while (first != null && first.item.equals(key)) {
            first = first.next;
            n--;
        }
        if (isEmpty()) {
            return;
        }

        Node current = first;
        while (current.next != null) {
            while (current.next.item.equals(key)) {
                current.next = current.next.next;
                n--;
            }
            current = current.next;
        }
    }

    public Integer max()
    {
        if (isEmpty()) {
            return 0;
        }
        Integer max = Integer.MIN_VALUE;
        Node current = first;
        while (current != null) {
            Integer t = (Integer) current.item;
            max = Math.max(max, t);
            current = current.next;
        }
        return max;
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

    private class ListIterator implements Iterator<Item>
    {
        private Node<Item> current;

        public ListIterator(Node<Item> first)
        {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator()
    {
        return new ListIterator(first);
    }

    public static void main(String[] args)
    {
        testDelete();
        testFind();
        testRemoveAfter();
        testInsertAfter();
        testRemove();
        testMax();
    }

    public static void testDelete() {
        List<Integer> linkedList = new List<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);

        StdOut.println("Before removing second node");

        StringJoiner listBeforeRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listBeforeRemove.add(String.valueOf(number));
        }

        StdOut.println(listBeforeRemove.toString());
        StdOut.println("Expected: 0 1 2 3");

        linkedList.delete(2);

        StdOut.println("\nAfter removing second node");
        StringJoiner listAfterRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listAfterRemove.add(String.valueOf(number));
        }

        StdOut.println(listAfterRemove.toString());
        StdOut.println("Expected: 0 2 3");
    }

    public static void testFind() {
        List<String> linkedList = new List<>();
        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");
        linkedList.add("D");

        StdOut.println("Find result: " + linkedList.find("B") + " Expected: true");
        StdOut.println("Find result: " + linkedList.find("Z") + " Expected: false");
    }

    public static void testRemoveAfter() {
        List<Integer> linkedList = new List<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        StdOut.println("Before removing node after node 0");

        StringJoiner listBeforeRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listBeforeRemove.add(String.valueOf(number));
        }

        StdOut.println(listBeforeRemove.toString());
        StdOut.println("Expected: 0 1 2 3 4");

        linkedList.removeAfter(0);

        StdOut.println("\nAfter removing node after node 0");

        StringJoiner listAfterRemove = new StringJoiner(" ");
        for (int number : linkedList) {
            listAfterRemove.add(String.valueOf(number));
        }

        StdOut.println(listAfterRemove.toString());
        StdOut.println("Expected: 0 2 3 4");
    }

    public static void testInsertAfter() {
        List<Integer> linkedList = new List<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        StdOut.println("Before inserting node 99 (after node 2)");

        StringJoiner listBeforeInsert = new StringJoiner(" ");
        for (int number : linkedList) {
            listBeforeInsert.add(String.valueOf(number));
        }

        StdOut.println(listBeforeInsert.toString());
        StdOut.println("Expected: 0 1 2 3 4");

        linkedList.insertAfter(2, 99);

        StdOut.println("\nAfter inserting node 99 (after node 2)");

        StringJoiner listAfterInsert = new StringJoiner(" ");
        for (int number : linkedList) {
            listAfterInsert.add(String.valueOf(number));
        }

        StdOut.println(listAfterInsert.toString());
        StdOut.println("Expected: 0 1 2 99 3 4");
    }

    public static void testRemove()
    {

        List<String> linkedList = new List<>();
        //remove
        linkedList.add("Mark");
        linkedList.add("Bill");
        linkedList.add("Elon");
        linkedList.add("Rene");
        linkedList.add("Mark");
        linkedList.add("Mark");
        linkedList.add("Mark");
        linkedList.add("Elon");

        StdOut.println("Before removing Mark");

        StringJoiner listBeforeRemove = new StringJoiner(" ");
        for (String name : linkedList) {
            listBeforeRemove.add(name);
        }

        StdOut.println(listBeforeRemove.toString());
        StdOut.println("Expected: Mark Bill Elon Rene Mark Mark Mark Elon");

        String itemToBeRemoved = "Mark";
        linkedList.remove(itemToBeRemoved);

        StdOut.println("\nAfter removing Mark");

        StringJoiner listAfterRemove = new StringJoiner(" ");
        for (String name : linkedList) {
            listAfterRemove.add(name);
        }

        StdOut.println(listAfterRemove.toString());
        StdOut.println("Expected: Bill Elon Rene Elon");
    }

    public static void testMax() {
        List<Integer> linkedList = new List<>();
        linkedList.add(3);
        linkedList.add(91);
        linkedList.add(2);
        linkedList.add(9);

        int maxValue = linkedList.max();
        StdOut.println("Max value: " + maxValue);
        StdOut.println("Expected: 91");
    }
}
