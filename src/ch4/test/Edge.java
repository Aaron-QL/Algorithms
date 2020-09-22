package ch4.test;

import edu.princeton.cs.algs4.StdOut;

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0 || w < 0) {
            throw new IllegalArgumentException();
        }

        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException();
        }

        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public int either() {
        return this.v;
    }

    public int other(int vertex) {
        if (vertex == this.v) {
            return this.w;
        } else if (vertex == this.w) {
            return this.v;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    /**
     * Unit tests the {@code Edge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        StdOut.println(e);
    }
}
