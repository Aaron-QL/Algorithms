package ch1.se2;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ex2 {
    public static void main(String[] args) {
        int n = 66;
        Interval1D[] intervals = new Interval1D[n];
        for (int i = 0; i < n; i++) {
            double d1 = StdRandom.uniform();
            double d2 = StdRandom.uniform();
            if (d1 > d2) {
                double t = d1;
                d1 = d2;
                d2 = t;
            }
            intervals[i] = new Interval1D(d1, d2);
        }

        StdOut.println("Pairs that intersect:");
        printIntervalIntersections(intervals);
    }

    private static void printIntervalIntersections(Interval1D[] intervals) {

        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = i + 1; j < intervals.length; j++) {

                if (intervals[i].intersects(intervals[j]) ) {
                    StdOut.printf("Interval 1 - Min: %.3f  Max: %.3f \n", intervals[i].min(), intervals[i].max());
                    StdOut.printf("Interval 2 - Min: %.3f  Max: %.3f \n", intervals[j].min(), intervals[j].max());
                    StdOut.println();
                }
            }
        }
    }
}
