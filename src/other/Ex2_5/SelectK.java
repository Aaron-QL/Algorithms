package other.Ex2_5;

import edu.princeton.cs.algs4.StdRandom;

public class SelectK {
    public static void main(String[] args) {
        Integer[] a = {1, 3, 6, 2, 1, 8, 2, 9, 3, 6, 7, 3, 6, 8, 1, 9};
        System.out.println(select(a, 0));
        System.out.println(select(a, 1));
        System.out.println(select(a, 2));
        System.out.println(select(a, 3));
        System.out.println(select(a, 4));
        System.out.println(select(a, 5));
        System.out.println(select(a, 6));
        System.out.println(select(a, 7));
        System.out.println(select(a, 8));
    }

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int j = partition(a, lo, hi);
            if (j == k) {
                return a[j];
            } else if (j > k) {
                hi = j - 1;
            } else {
                lo = j + 1;
            }
        }

        return a[k];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
