package ch4.se4;

import ch1.se3.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.NoSuchElementException;

public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adjacent;
    private int[] inDegree;

    public EdgeWeightedDigraph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException();
        }

        this.V = v;
        adjacent = new Bag[v];
        for (int i = 0; i < v; i++) {
            adjacent[i] = new Bag<DirectedEdge>();
        }
        inDegree = new int[v];
    }


    public EdgeWeightedDigraph(int v, int e) {
        this(v);
        if (e < 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < e; i++) {
            int u = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = StdRandom.uniform(100) * 0.01;
            DirectedEdge edge = new DirectedEdge(u, w, weight);
            this.addEdge(edge);
        }
    }

    public EdgeWeightedDigraph(In in) {
        if (in == null) {
            throw new IllegalArgumentException();
        }

        try {
            this.V = in.readInt();
            if (V < 0) {
                throw new IllegalArgumentException();
            }
            adjacent = new Bag[V];
            for (int i = 0; i < V; i++) {
                adjacent[i] = new Bag<DirectedEdge>();
            }
            inDegree = new int[V];

            int E = in.readInt();
            if (E < 0) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                double weight = in.readDouble();
                addEdge(new DirectedEdge(v, w, weight));
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
    }

    private void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        this.adjacent[v].add(e);
        this.inDegree[w]++;
        this.E++;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return this.adjacent[v];
    }

    public int inDegree(int v) {
        validateVertex(v);
        return this.inDegree[v];
    }

    public int outDegree(int v) {
        validateVertex(v);
        return this.adjacent[v].size();
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge e : this.adjacent[i]) {
                list.add(e);
            }
        }

        return list;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adjacent[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
//        In in = new In(args[0]);
//        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
//        StdOut.println(G);

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(11, 14);
        StdOut.println(G);
    }
}
