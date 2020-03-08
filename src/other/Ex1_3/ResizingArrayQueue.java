package other.Ex1_3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<E> implements Iterable<E> {

    public static void main(String[] args) {
        ResizingArrayQueue<String> q = new ResizingArrayQueue<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                q.enqueue(item);
            } else if (!q.isEmpty()) {
                StdOut.println(q.dequeue() + " ");
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }

    private E[] queue;

    private int head = 0;
    private int tail = 0;
    private int n = 0;

    ResizingArrayQueue() {
        queue = (E[]) new Object[2];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize() {
        E[] temp = (E[]) new Object[n + n / 2 + 1];
        int j = 0;
        for (int i = tail; i < head; i++) {
            temp[j++] = queue[i];
        }
        queue = temp;
        tail = 0;
        head = n;
    }

    void enqueue(E item) {
        if (head == queue.length) {
            resize();
        }
        queue[head++] = item;
        n++;
    }

    E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        E item = queue[tail];
        queue[tail++] = null;
        n--;

        if (n == queue.length / 3) {
            resize();
        }

        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        return queue[head];
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private int i = tail;

        @Override
        public boolean hasNext() {
            return i != head;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return queue[i++];
        }
    }
}
