package other.Ex2_1;

import edu.princeton.cs.algs4.StdOut;

public class Heap {

    public static void main(String[] args) {
        Integer[] a = {1, 3, 2, 11, 9, 1, 1, 4, 2, 8};
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int k = n / 2; k >= 0; k--) {
            sink(a, k, n);
        }

        while (n > 1) {
            exch(a, 0, --n);
            sink(a, 0, n);
        }
    }

    private static void sink(Comparable[] a, int k, int n) {
        int largest = k;
        int left = k * 2 + 1;
        int right = left + 1;

        if (left < n && less(a[largest], a[left])) {
            largest = left;
        }

        if (right < n && less(a[largest], a[right])) {
            largest = right;
        }

        if (largest != k) {
            exch(a, k, largest);
            sink(a, largest, n);
        }
    }

    private static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
