package ch4.se2;

import ch1.se3.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    private int[] indegree;

    public Digraph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException();
        }
        this.V = v;
        this.E = 0;
        this.adj = (Bag<Integer>[]) new Bag[v];
        this.indegree = new int[v];
        for (int i = 0; i < v; i++) {
            this.adj[i] = new Bag<Integer>();
        }
    }

    public Digraph(In in) {
        if (in == null) {
            throw new IllegalArgumentException();
        }
        try {
            this.V = in.readInt();
            if (this.V < 0) {
                throw new IllegalArgumentException();
            }
            this.adj = (Bag<Integer>[]) new Bag[this.V];
            this.indegree = new int[this.V];
            for (int i = 0; i < this.V; i++) {
                this.adj[i] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                this.addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
    }

    public void addEdge(int v, int w) {
        this.validateVertex(v);
        this.validateVertex(w);
        this.adj[v].add(w);
        this.indegree[w]++;
        this.E++;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public Iterable<Integer> adj(int v) {
        this.validateVertex(v);
        return this.adj[v];
    }

    public int outDegree(int v) {
        this.validateVertex(v);
        return this.adj[v].size();
    }

    public int inDegree(int v) {
        this.validateVertex(v);
        return this.indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code Digraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
    }
}
