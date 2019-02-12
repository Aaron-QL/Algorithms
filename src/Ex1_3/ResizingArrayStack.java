package Ex1_3;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item>{
    private int n = 0;
    private Item[] a;

    public static void main(String[] args) {
        ResizingArrayStack<String> s = new ResizingArrayStack<>(2);
        s.push("q");
        s.push("q");
        s.push("q");
        s.push("q");
        for (String item : s) {
            System.out.println(item);
            s.push(item);
        }
        for (String item : s) {
            System.out.println(item);
        }
    }

    public ResizingArrayStack(int cap) {
        this.a = (Item[]) new Object[cap];
    }

    public void push(Item item) {
        if (n == a.length) {
            resize(n * 2);
        }
        a[n++] = item;
    }

    public Item pop() {
        Item item = a[--n];
        a[n] = null;
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public boolean isFull() {
        return n == a.length;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = n;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
