package Ex2_1;

import edu.princeton.cs.algs4.StdOut;

public class Shell {

    public static void main(String[] args) {
        String[] a = {"a", "b", "a", "d", "w", "e"};
        sort(a);
        assert isSorted(a);
        show(a);
    }

    public static void sort(Comparable[] a) {
        int n = a.length;

        int gap = 1;
        while (gap < n / 3) {
            gap = gap * 3 + 1;
        }

        while (gap >= 1) {
            for (int i = gap; i < n; i++) {
                for (int j = i; j > gap && less(a[j], a[j - gap]); j -= gap) {
                    exch(a, j, j - gap);
                }
            }
            gap /= 3;
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
