package other.Ex1_2;

import edu.princeton.cs.algs4.*;

public class Ex3 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);

        Point2D[] leftTopPoints = new Point2D[n];
        Point2D[] rightBottomPoints = new Point2D[n];
        Interval2D[] intervals = new Interval2D[n];

        for (int i = 0; i<n; i++) {
            double a = StdRandom.uniform(min, max);
            double b = StdRandom.uniform(min, max);
            double left, right, top, bottom;
            Interval1D x, y;
            left = a < b ? a : b;
            right = a < b ? b : a;
            x = new Interval1D(left, right);
            double c = StdRandom.uniform(min, max);
            double d = StdRandom.uniform(min, max);
            top = c < d ? c : d;
            bottom = c < d ? d : c;
            y = new Interval1D(top, bottom);
            leftTopPoints[i] = new Point2D(left, top);
            rightBottomPoints[i] = new Point2D(right, bottom);
            intervals[i] = new Interval2D(x, y);
            intervals[i].draw();
        }

        int containNum = 0, intervalNum = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i && intervals[i].intersects(intervals[j])) {
                    intervalNum++;
                }
                if (j != i && intervals[i].contains(leftTopPoints[j]) && intervals[i].contains(rightBottomPoints[j])) {
                    containNum++;
                }
            }
        }
        System.out.println("Interval count: " + intervalNum);
        System.out.println("Contain count: " + containNum);
    }
}
