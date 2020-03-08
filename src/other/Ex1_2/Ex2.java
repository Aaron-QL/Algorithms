package other.Ex1_2;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;

public class Ex2 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Interval1D[] intervals = new Interval1D[n];

        for (int i = 0; i < n; i++) {
            intervals[i] = new Interval1D(StdIn.readDouble(), StdIn.readDouble());
        }

        if (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (intervals[i].intersects(intervals[j])) {
                        System.out.println(intervals[i] + " intersects " + intervals[j]);
                    }
                }
            }
        }
    }
}
