package ch1.se1;

import java.util.Arrays;

public class ex15 {
    public static void main(String[] args) {
        int[] t = {1, 3, 5, 3, 5, 4, 6, 1};
        int[] r = histogram(t, 3);
        System.out.print(Arrays.toString(r));
    }

    public static int[] histogram(int[] a, int m)
    {
        int[] arr = new int[m];
        for (int i : a) {
            if (i >= 0 && i < m) {
                arr[i]++;
            }
        }
        return arr;
    }
}
