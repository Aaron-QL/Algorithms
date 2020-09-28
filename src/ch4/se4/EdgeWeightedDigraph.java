package ch4.se4;

import ch1.se3.Bag;
import ch4.test.Edge;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

public class EdgeWeightedDigraph {
    private final int vertices;
    private Bag<DirectedEdge>[] adjacent;
    private int edges;
    private int[] indegree;

    public EdgeWeightedDigraph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException();
        }
        vertices = v;
        this.indegree = new int[v];
        adjacent = (Bag<DirectedEdge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adjacent[i] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
        if (in == null) {
            throw new IllegalArgumentException();
        }

        try {
            int v = in.readInt();
            if (v < 0) {
                throw new IllegalArgumentException();
            }
            vertices = v;
            this.indegree = new int[v];
            adjacent = (Bag<DirectedEdge>[]) new Bag[v];
            for (int i = 0; i < v; i++) {
                adjacent[i] = new Bag<>();
            }

            int e = in.readInt();
            if (e < 0) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < e; i++) {
                int from = in.readInt();
                int to = in.readInt();
                double weight = in.readDouble();
                DirectedEdge edge = new DirectedEdge(from, to, weight);
                this.addEdage(edge);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
    }

    public void addEdage(DirectedEdge e) {
        int from = e.from();
        int to = e.to();
        this.check(from);
        this.check(to);
        this.adjacent[from].add(e);
        this.indegree[to]++;
        this.edges++;
    }

    public int vertices() {
        return this.vertices;
    }

    public int edges() {
        return this.edges;
    }

    public void check(int vertex) {
        if (vertex < 0 || vertex >= this.vertices) {
            throw new IllegalArgumentException();
        }
    }

    public int indegree(int v) {
        this.check(v);
        return this.indegree[v];
    }

    public Iterable<DirectedEdge> adjacent(int vertex) {
        this.check(vertex);
        return this.adjacent[vertex];
    }
}
