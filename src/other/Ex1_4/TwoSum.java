package other.Ex1_4;

import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {
        int[] a = {-3, 0, 5, -6, 9, -8, 11, 13};
        System.out.println(count(a));
    }

    public static int count(int[] a) {
        Arrays.sort(a);
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (BinarySearch.rank(-a[i], a) > i) {
                count++;
            }
        }
        return count;
    }

}
