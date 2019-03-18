package offer;

public class Q12 {
    private final static int[][] next = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int rows;
    private int cols;

    public boolean hasPath(char[] a, int rows, int cols, char[] strs) {
        if (rows == 0 || cols == 0) {
            return false;
        }
        this.rows = rows;
        this.cols = cols;
        char[][] matrix = buildMatrix(a);
        boolean[][] marked = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backTracking(matrix, strs, marked, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backTracking(char[][] matrix, char[] strs, boolean[][] marked, int pathLen, int r, int c) {
        if (pathLen == strs.length) {
            return true;
        }
        if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] != strs[pathLen] || marked[r][c]) {
            return false;
        }
        marked[r][c] = true;
        for (int[] n : next) {
            if (backTracking(matrix, strs, marked, pathLen + 1, r + n[0], c + n[1])) {
                return true;
            }
        }
        marked[r][c] = false;
        return false;
    }

    private char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][cols];
        for (int i = 0, idx = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = array[idx++];
            }
        }
        return matrix;
    }
}
