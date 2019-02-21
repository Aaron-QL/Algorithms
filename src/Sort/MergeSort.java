package Sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 6, 0, 5, 7, 2};
        System.out.println(Arrays.toString(mergeSort(arr)));
    }

    public static int[] mergeSort(int[] a) {
        int n = a.length;
        if (n < 2) {
            return a;
        }
        int mid = n / 2;
        int[] l = Arrays.copyOfRange(a, 0, mid);
        int[] r = Arrays.copyOfRange(a, mid, n);


        l = mergeSort(l);
        r = mergeSort(r);

        return merge(l, r);
    }

    public static int[] merge(int[] l, int[] r) {
        int[] res = new int[l.length + r.length];
        int i = 0, j = 0, k = 0;
        while (i < l.length && k < r.length) {
            if (l[i] > r[j]) {
                res[k++] = r[j++];
            } else {
                res[k++] = l[i++];
            }
        }

        while (i < l.length) {
            res[k++] = l[i++];
        }

        while (j < r.length) {
            res[k++] = r[j++];
        }

        return res;
    }
}
