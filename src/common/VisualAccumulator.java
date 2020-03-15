package common;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * an abstract data type for accumulating data values (visual version)
 */

public class VisualAccumulator {
    private double total;
    private int N;
    private Queue<Double> q;

    public VisualAccumulator(int trials, double max) {
        StdDraw.setXscale(0, trials + 3);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
        q = new Queue<>();
    }

    public void addDataValue(double val) {
        StdOut.println(val);
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total/N);
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "Mean (" + N + " values): " + String.format("%7.5f", mean());
    }

}