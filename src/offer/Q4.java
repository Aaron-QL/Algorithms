package offer;

public class Q4 {
    static boolean find(int[][] arr, int target) {
        if (arr == null || arr.length == 0 || arr[0].length == 0) {
            return false;
        }

        int rows = arr.length;
        int cols = arr[0].length;
        int r = 0, c = cols - 1;
        while (r < rows && c >= 0) {
            if (arr[r][c] == target) {
                return true;
            } else if (arr[r][c] > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }
}
