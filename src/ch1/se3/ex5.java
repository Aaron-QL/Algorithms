package ch1.se3;

import edu.princeton.cs.algs4.StdOut;

public class ex5 {
    private static void decimalToBinary(int n) {
        Stack<Integer> stack = new Stack<Integer>();

        while (n > 0) {
            stack.push(n % 2);
            n /= 2;
        }

        for (int d : stack) {
            StdOut.print(d);
        }
        StdOut.println();
    }

    public static void main(String[] args) {
        decimalToBinary(50);
        StdOut.println("Expected: 110010");
    }
}
