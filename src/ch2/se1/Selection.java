package ch2.se1;

import edu.princeton.cs.algs4.StdOut;

public class Selection {

    public static void main(String[] args) {
        String[] a = {"a", "b", "a", "aref", "a", "123"};
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int i, j, min;
        for (i = 0; i < a.length - 1; i++) {
            min = i;
            for (j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) {
                return false;
            }
        }
        return true;
    }
}
