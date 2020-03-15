package ch1.se3;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1];
    private int N = 0;

    public static void main(String[] args) {

    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    public void resize(int max)
    {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item)
    {
        if (N == a.length) {
            resize(N * 2);
        }
        a[N++] = item;
    }

    public Item pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("stack is empty");
        }
        Item item = a[--N];
        a[N] = null;
        if (N > 0 && N < a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    private class ReverseArrayIterator implements Iterator<Item>
    {
        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
}
