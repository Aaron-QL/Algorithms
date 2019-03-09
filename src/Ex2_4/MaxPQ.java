package Ex2_4;

import java.util.Arrays;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n = 0;

    public static void main(String[] args) {
        MaxPQ<Integer> pq = new MaxPQ<>(9);
        pq.insert(4);
        pq.insert(3);
        pq.insert(1);
        pq.insert(3);
        pq.insert(2);
        pq.insert(3);
        pq.insert(8);
        pq.insert(5);
        pq.insert(1);

        System.out.println(Arrays.toString(pq.pq));

    }

    MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    void insert(Key v) {
        pq[++n] = v;
        swim(n);
    }

    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }

    void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k/2, k);
            k /= 2;
        }
    }

    void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            exch(k, j);
            k = j;
        }
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, n--);
        pq[n + 1] = null;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
