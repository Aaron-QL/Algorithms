package Sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 6, 0, 5, 7, 2};
        System.out.println(Arrays.toString(sort(arr)));
    }

    static int[] sort(int[] arr) {
        int n = arr.length;
        int temp;
        int gap = 1;
        while (gap < n / 3) {
            gap = gap * 3 + 1;
        }

        return arr;
    }
}
