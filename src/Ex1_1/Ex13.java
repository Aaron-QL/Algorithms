package Ex1_1;

public class Ex13 {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {2, 4, 6}
        };
        int[][] b = Ex13.convert(a);
        for (int[] i : b) {
            for (int t : i) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    static int[][] convert(int[][] arr) {
        int m = arr[0].length;
        int n = arr.length;
        int[][] result = new int[m][];
        for (int i = 0; i < m; i++) {
            int[] item = new int[n];
            for (int j = 0; j < n; j++) {
                item[j] = arr[j][i];
            }
            result[i] = item;
        }
        return result;
    }
}
