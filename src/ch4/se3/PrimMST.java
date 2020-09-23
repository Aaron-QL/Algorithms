package ch4.se3;


import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Queue;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        int v = graph.V();
        this.edgeTo = new Edge[v];
        this.distTo = new double[v];
        this.marked = new boolean[v];
        this.pq = new IndexMinPQ<>(v);

        for (int i = 0; i < v; i++) {
            this.distTo[i] = Double.POSITIVE_INFINITY;
        }

        for (int i = 0; i < v; i++) {
            if (!this.marked[i]) {
                this.prim(graph, i);
            }
        }
    }

    private void prim(EdgeWeightedGraph graph, int s) {
        this.distTo[s] = 0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            this.scan(graph, pq.delMin());
        }
    }

    private void scan(EdgeWeightedGraph graph, int v) {
        this.marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (this.marked[w]) {
                continue;
            }

            if (e.weight() < this.distTo[w]) {
                this.distTo[w] = e.weight();
                this.edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.changeKey(w, e.weight());
                } else {
                    pq.insert(w, e.weight());
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }
}
