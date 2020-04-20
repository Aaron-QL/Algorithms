package ch2.se4;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public static void main(String[] args) {

    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int maxN) {
        if (maxN < N) {
            throw new StackOverflowError();
        }
        Key[] temp = (Key[]) new Comparable[maxN];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
        if (size() > pq.length / 2) {
            resize(pq.length * 2);
        }
    }

    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Key k = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        if (pq.length > 16 && N < pq.length / 4) {
            resize(pq.length / 2);
        }
        return k;
    }

    private void sink(int k) {
        while (k * 2 <= N) {
            int j = k * 2;
            if (j < N && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
}
