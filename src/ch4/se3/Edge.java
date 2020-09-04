package ch4.se3;

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0 || w < 0) {
            throw new IllegalArgumentException();
        }
        this.v = v;
        this.w = w;
        this.weight = weight;
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
            throw new RuntimeException("Inconsistent edge");
        }
    }

    public double weight() {
        return this.weight;
    }

    public int compareTo(Edge that) {
        if (this.weight > that.weight()) {
            return 1;
        } else if (this.weight == that.weight()) {
            return 0;
        } else {
            return -1;
        }
    }

    public String toString() {
        return String.format("%d-%d %.2f", this.v, this.w, this.weight);
    }
}
