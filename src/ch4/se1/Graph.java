package ch4.se1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;
import java.util.Stack;

@SuppressWarnings("unchecked")
public class Graph {

    private final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E = 0;
    private Bag<Integer>[] adj;


    public Graph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }
        this.V = V;
        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        if (in == null) {
            throw new IllegalArgumentException("argument is null");
        }
        try {
            this.V = in.readInt();
            if (V < 0) {
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            }
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }

            int j = in.readInt();
            if (j < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for (int i = 0; i < j; i++) {
                int v = in.readInt();
                int w = in.readInt();
                this.addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    public Graph(Graph G) {
        this.V = G.V();
        this.E = G.E();
        if (this.V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }

        this.adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < this.V; v++) {
            this.adj[v] = new Bag<Integer>();
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                this.adj[v].add(w);
            }
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(int v, int w) {
        this.validateVertex(v);
        this.validateVertex(w);
        this.adj[v].add(w);
        this.adj[w].add(v);
        this.E++;
    }

    public Iterable<Integer> adj(int v) {
        this.validateVertex(v);
        return this.adj[v];
    }

    public int degree(int v) {
        this.validateVertex(v);
        return this.adj[v].size();
    }

    public int maxDegree() {
        int max = 0;
        for (int v = 0; v < V; v++) {
            int t = this.degree(v);
            if (max < t) {
                max = t;
            }
        }
        return max;
    }

    public double avgDegree() {
        return 2 * this.E() / this.V();
    }

    public int numberOfSelfLoops() {
        int count = 0;
        for (int v = 0; v < this.V; v++) {
            for (int w : this.adj[v]) {
                if (v == w) {
                    count ++;
                }
            }
        }
        return count / 2;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.V + " vertices, " + this.E + " edges" + this.NEWLINE);
        for (int v = 0; v < this.V; v++) {
            s.append(v + ":");
            for (int w : adj[v]) {
                s.append(" " + w);
            }
            s.append(this.NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
    }
}
