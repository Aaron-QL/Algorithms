package offer;

import java.util.Arrays;

public class Q3 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 0, 2};
        System.out.println(duplicate(nums));
    }
    public static int duplicate(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                exch(nums, i, nums[i]);
                System.out.println(Arrays.toString(nums));
            }
        }
        return -1;
    }

    private static void exch(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


}
