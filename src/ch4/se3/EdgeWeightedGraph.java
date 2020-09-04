package ch4.se3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E = 0;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException();
        }

        this.V = v;
        this.adj = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in) {
        if (in == null) {
            throw new IllegalArgumentException();
        }

        try {
            int v = in.readInt();
            this.V = v;
            this.adj = (Bag<Edge>[]) new Bag[v];
            for (int i = 0; i < v; i++) {
                this.adj[i] = new Bag<Edge>();
            }

            int edges = in.readInt();
            if (v < 0 || edges < 0) {
                throw new IllegalArgumentException();
            }


            for (int i = 0; i < edges; i++) {
                int x = in.readInt();
                int y = in.readInt();
                double w = in.readDouble();
                this.validateVertex(x);
                this.validateVertex(y);
                Edge e = new Edge(x, y, w);
                this.addEdge(e);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        this.validateVertex(v);
        this.validateVertex(w);

        this.adj[v].add(e);
        this.adj[w].add(e);
        this.E++;
    }

    public Iterable<Edge> adj(int v) {
        this.validateVertex(v);
        return this.adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<Edge>();
        for (int i = 0; i < this.V; i++) {
            for (Edge e : this.adj[i]) {
                if (e.other(i) > i) {
                    b.add(e);
                }
            }
        }
        return b;
    }

    public int degree(int v) {
        this.validateVertex(v);
        return this.adj[v].size();
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }
    /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code EdgeWeightedGraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        StdOut.println(G);
    }
}
