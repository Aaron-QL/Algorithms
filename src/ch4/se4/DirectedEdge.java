package ch4.se4;


public class DirectedEdge {
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        if (v < 0 || w < 0 || Double.isNaN(weight)) {
            throw new IllegalArgumentException();
        }

        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    public String toString() {
        return String.format("%d -> %d %5.2f", v, w, weight);
    }
}
