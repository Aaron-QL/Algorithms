package Sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 6, 0, 5, 7, 2};
        System.out.println(Arrays.toString(sort(arr)));
    }

    static int[] sort(int[] arr) {
        int len = arr.length;
        int tempIndex, temp;

        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i; j < len; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }
}
