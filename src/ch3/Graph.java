package ch3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Graph {
    private final String NEWLINE = System.getProperty("line.separator");
    private final int v;
    private int e = 0;
    private Bag<Integer>[] adj;


    public Graph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException();
        }
        this.v = v;
        this.adj = (Bag<Integer>[]) new Bag[v];

        for (int i = 0; i < this.v; i++) {
            this.adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        if (in == null) {
            throw new IllegalArgumentException();
        }
        try {
            this.v = in.readInt();
            if (this.v < 0) {
                throw new IllegalArgumentException();
            }
            int j = in.readInt();
            if (j < 0) {
                throw new IllegalArgumentException();
            }

            for (int i = 0; i < this.v; i++) {
                this.adj[i] = new Bag<Integer>();
            }

            for (int i = 0; i < j; i++) {
                int v = in.readInt();
                int w = in.readInt();
                this.addEdage(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
    }

    public void addEdage(int v, int w) {
        this.validateVertex(v);
        this.validateVertex(w);
        this.adj[v].add(w);
        this.adj[w].add(v);
        this.e++;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= this.v) {
            throw new IllegalArgumentException();
        }
    }

    public int V() {
        return this.v;
    }

    public int E() {
        return this.e;
    }

    public Iterable<Integer> adj(int v) {
        this.validateVertex(v);
        return this.adj[v];
    }
}
