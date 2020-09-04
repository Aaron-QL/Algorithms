package ch4.test;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Graph implements GraphInterface{
    private final int V;
    private int E = 0;
    private Bag<Integer>[] adjacent;
    private final String NEWLINE = System.getProperty("line.separator");

    public Graph(int v) {
        if (v < 0) {
            throw new IllegalArgumentException();
        }
        this.V = v;
        this.adjacent = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            this.adjacent[i] = new Bag<Integer>();
        }
    }

    public Graph (In in) {
        if (in == null) {
            throw new IllegalArgumentException();
        }

        try {
            this.V =  in.readInt();
            if (this.V < 0) {
                throw new IllegalArgumentException();
            }
            this.adjacent = (Bag<Integer>[]) new Bag[this.V];
            for (int i = 0; i < this.V; i++) {
                this.adjacent[i] = new Bag<Integer>();
            }

            int e = in.readInt();
            for (int i = 0; i < e; i++) {
                int v = in.readInt();
                int w = in.readInt();
                this.addEdge(v, w);
            }

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException();
        }
    }

    public void addEdge(int v, int w) {
        this.check(v);
        this.check(w);
        this.adjacent[v].add(w);
        this.adjacent[w].add(v);
        this.E++;
    }

    private void check(int v) {
        if (v < 0 || v >= this.V) {
            throw new IllegalArgumentException();
        }
    }

    public Iterable<Integer> adjacent(int v) {
        this.check(v);
        return this.adjacent[v];
    }

    public int degree(int v) {
        return this.adjacent[v].size();
    }

    public int vertices() {
        return this.V;
    }

    public int edges() {
        return this.E;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int vertex = 0; vertex < vertices(); vertex++) {
            stringBuilder.append(vertex).append(": ");

            for(int neighbor : this.adjacent(vertex)) {
                stringBuilder.append(neighbor).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
