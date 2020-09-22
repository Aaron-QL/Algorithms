package ch4.test;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdRandom;

public class EdgeWeightedGraph {
    private final int vertices;
    private int edges;
    private Bag<Edge>[] adjacent;
    private final String NEWLINE = System.getProperty("line.separator");

    public EdgeWeightedGraph(int vertices) {
        if (vertices < 0) {
            throw new IllegalArgumentException();
        }
        this.vertices = vertices;
        this.adjacent = (Bag<Edge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            this.adjacent[i] = new Bag<>();
        }
    }

    public EdgeWeightedGraph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int e = StdRandom.uniform(V);
            double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
            Edge edge = new Edge(v, e, weight);
            this.addEdge(edge);
        }
    }

    private void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        this.check(v);
        this.check(w);
        this.adjacent[v].add(edge);
        this.adjacent[w].add(edge);
        this.edges++;
    }

    private void check(int v) {
        if (v < 0 || v >= this.vertices) {
            throw new IllegalArgumentException();
        }
    }

    public int vertices() {
        return this.vertices;
    }

    public int edges() {
        return this.edges;
    }

    public Iterable<Edge> adjacent(int vertex) {
        this.check(vertex);
        return this.adjacent[vertex];
    }
}
