package ch1.se2;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class ex1 {
    public static void main(String[] args) {
        int n = 11111;
        Point2D[] points = new Point2D[n];

        StdDraw.setCanvasSize(512, 512);
        StdDraw.setPenRadius(0.05);
        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        for (int i = 0; i < n; i++) {
            double pointX = StdRandom.uniform();
            double pointY = StdRandom.uniform();
            Point2D point = new Point2D(pointX, pointY);
            StdDraw.point(point.x(), point.y());
            points[i] = point;
        }

        StdOut.printf("The shortest distance is: %.3f", calculateShortestDistance(points));
    }

    private static double calculateShortestDistance(Point2D[] points)
    {
        double shortestDistance = Double.MAX_VALUE;
        double currentDistance;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                currentDistance = points[i].distanceTo(points[j]);
                shortestDistance = Math.min(shortestDistance, currentDistance);
            }
        }

        return shortestDistance;
    }
}
