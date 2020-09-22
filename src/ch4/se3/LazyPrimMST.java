package ch4.se3;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private double weight;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;
    private boolean[] marked;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        this.mst = new Queue<>();
        this.pq = new MinPQ<>();
        this.marked = new boolean[graph.V()];

        for (int i = 0; i < graph.V(); i++) {
            if (!this.marked[i]) {
                this.prim(graph, i);
            }
        }
    }

    private void prim(EdgeWeightedGraph graph, int s) {
        visit(graph, s);

        while (!this.pq.isEmpty()) {
            Edge e = this.pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (this.marked[v] && this.marked[w]) {
                continue;
            }
            this.mst.enqueue(e);
            if (!this.marked[v]) {
                visit(graph, v);
            }
            if (!this.marked[w]) {
                visit(graph, w);
            }
            this.weight += e.weight();
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        this.marked[v] = true;
        for (Edge e : graph.adj(v)) {
            if (!this.marked[e.other(v)]) {
                this.pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return this.mst;
    }

    public double weight() {
        return this.weight;
    }
}
