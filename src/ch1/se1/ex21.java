package ch1.se1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ex21 {
    public static void main(String[] args) {
        In in = new In();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] values = line.split(" ");
            formatedPrint(values);
        }
    }

    private static void formatedPrint(String[] values)
    {
        StdOut.printf("%8s: %8s %8s ", values[0], values[1], values[2]);
        double result = Double.parseDouble(values[1]) / Double.parseDouble(values[2]);
        StdOut.printf("%7.3f\n", result);
    }
}
