package ch4.se4;

public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int from, int to, double weight) {
        if (from < 0 || to < 0) {
            throw new IllegalArgumentException();
        }
        this.v = from;
        this.w = to;
        this.weight = weight;
    }

    public int from() {
        return this.v;
    }

    public int to() {
        return this.w;
    }
}
