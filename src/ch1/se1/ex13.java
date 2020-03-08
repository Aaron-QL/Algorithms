package ch1.se1;


public class ex13 {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {2, 4, 6}
        };
        int[][] b = ex13.convert(a);
        for (int[] i : b) {
            for (int t : i) {
                System.out.print(t + " ");
            }
            System.out.println();
        }
    }

    public static int[][] convert(int[][] arr)
    {
        int m = arr[0].length;
        int n = arr.length;
        int[][] result = new int[m][];
        for (int i = 0; i < m; i++) {
            int[] row = new int[n];
            for (int j = 0; j < n; j++) {
                row[j] = arr[j][i];
            }
            result[i] = row;
        }
        return result;
    }
}
