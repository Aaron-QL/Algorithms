package Ex1_2;


import edu.princeton.cs.algs4.Point2D;

public class Ex1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Point2D[] points = new Point2D[n];

        for (int i = 0; i < n; i++) {
            points[i] = new Point2D(Math.random(), Math.random());
            points[i].draw();
        }

        if (n > 1) {
            double minDistance = points[0].distanceTo(points[1]);
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    minDistance = Math.min(points[i].distanceTo(points[j]), minDistance);
                }
            }
            System.out.println("min distance: " + minDistance);
        }

    }
}
