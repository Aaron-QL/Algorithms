package ch1.se2;

import edu.princeton.cs.algs4.*;

import java.util.HashMap;
import java.util.Map;

public class ex3 {
    private static int n;
    private static double min;
    private static double max;

    private static Map<Interval2D, Interval1D[]> intervalMap = new HashMap<>();

    public static void main(String[] args) {
        n = 6;
        min = 1;
        max = 66;

        Interval2D[] intervals = new Interval2D[n];
        drawAndCreateIntervals(intervals);

        int[] results = countIntervalIntersectionsAndContains(intervals);

        StdOut.println("Pairs of intervals that intersect: " + results[0]);
        StdOut.println("Intervals contained in one another: " + results[1]);
    }

    private static void drawAndCreateIntervals(Interval2D[] intervals)
    {
        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setPenRadius(0.0015);
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);

        for (int i = 0; i < intervals.length; i++) {
            Interval1D width = generateInterval1D();
            Interval1D height = generateInterval1D();
            Interval2D interval = new Interval2D(width, height);
            interval.draw();
            intervals[i] = interval;

            intervalMap.put(interval, new Interval1D[]{width, height});
        }
    }

    private static Interval1D generateInterval1D()
    {
        double d1 = StdRandom.uniform(min, max);
        double d2 = StdRandom.uniform(min, max);

        if (d1 > d2) {
            double t = d1;
            d1 = d2;
            d2 = t;
        }

        return new Interval1D(d1, d2);
    }

    private static int[] countIntervalIntersectionsAndContains(Interval2D[] intervals)
    {
        int[] result = new int[2];
        for (int i = 0; i < intervals.length - 1; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                if (intervals[i].intersects(intervals[j])) {
                    result[0]++;
                }
                if (isContained(intervals[i], intervals[j])) {
                    result[1]++;
                }
            }
        }

        return result;
    }

    private static boolean isContained(Interval2D i1, Interval2D i2)
    {
        Interval1D[] p1 = intervalMap.get(i1);
        Interval1D[] p2 = intervalMap.get(i2);

        if ((p1[0].min() < p2[0].min()
                && p1[0].max() > p2[0].max()
                && p1[1].min() < p2[1].min()
                && p1[1].max() > p2[1].max())
                ||
            (p1[0].min() > p2[0].min()
                        && p1[0].max() < p2[0].max()
                        && p1[1].min() > p2[1].min()
                        && p1[1].max() < p2[1].max())
        ) {
            return true;
        }
        return false;
    }
}
