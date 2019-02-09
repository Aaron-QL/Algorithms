package Ex1_3;

import java.util.Iterator;

public class StandardStack<T> implements Iterable<T>{
    private int ptr = 0;
    private T[] stack;

    public static void main(String[] args) {
        StandardStack<String> s = new StandardStack<>(2);
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

    public StandardStack(int cap) {
        this.stack = (T[]) new Object[cap];
    }

    public void push(T item) {
        if (ptr == stack.length) {
            resize(ptr * 2);
        }
        stack[ptr++] = item;
    }

    public T pop() {
        T item = stack[--ptr];
        stack[ptr] = null;
        if (ptr > 0 && ptr == stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return ptr == 0;
    }

    public int size() {
        return ptr;
    }

    public boolean isFull() {
        return ptr == stack.length;
    }

    private void resize(int max) {
        T[] temp = (T[]) new Object[max];
        for (int i = 0; i < ptr; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {

        private int i = ptr;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return stack[--i];
        }
    }
}
