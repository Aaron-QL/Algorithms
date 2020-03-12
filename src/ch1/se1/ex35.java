package ch1.se1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ex35 {
    public static void main(String[] args) {
        int sides = 6;
        double[] dist = new double[2 * sides + 1];
        for (int i = 1; i <= sides; i++) {
            for (int j = 1; j <= sides; j++) {
                dist[i+j] += 1.0;
            }
        }

        for (int k = 2; k <= 2*sides; k++) {
            dist[k] /= 36.0;
        }

        StdOut.println(Arrays.toString(dist));
    }
}
