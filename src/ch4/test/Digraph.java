package ch4.test;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Digraph implements DigraphInterface {
    private final int vertices;
    private int edges = 0;
    private Bag<Integer>[] adjacent;
    private final String NEWLINE = System.getProperty("line.separator");
    private int[] indegrees;
    private int[] outdegrees;

    public Digraph(int vertices) {
        if (vertices < 0) {
            throw new IllegalArgumentException();
        }
        this.vertices = vertices;
        this.adjacent = (Bag<Integer>[]) new Bag[vertices];
        this.indegrees = new int[vertices];
        this.outdegrees = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            this.adjacent[i] = new Bag<>();
        }
    }

    public Digraph(In in) {
        if (in == null) {
            throw new IllegalArgumentException();
        }

        try {
            this.vertices = in.readInt();
            if (this.vertices < 0) {
                throw new IllegalArgumentException();
            }
            this.adjacent = (Bag<Integer>[]) new Bag[vertices];
            this.indegrees = new int[vertices];
            this.outdegrees = new int[vertices];

            for (int i = 0; i < vertices; i++) {
                this.adjacent[i] = new Bag<>();
            }

            int edges = in.readInt();
            for (int i = 0; i < edges; i++) {
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
        this.indegrees[w]++;
        this.outdegrees[v]++;
        this.edges++;
    }

    private void check(int v) {
        if (v < 0 || v >= this.vertices) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int vertices() {
        return this.vertices;
    }

    @Override
    public int edges() {
        return this.edges;
    }

    @Override
    public Iterable<Integer> adjacent(int vertex) {
        return this.adjacent[vertex];
    }

    public Bag<Integer>[] getAdjacencyList() {
        return adjacent;
    }

    public int indegree(int vertex) {
        return indegrees[vertex];
    }

    public int outdegree(int vertex) {
        return outdegrees[vertex];
    }

    public Digraph reverse() {
        Digraph reverse = new Digraph(vertices);

        for(int vertex = 0; vertex < vertices; vertex++) {
            for(int neighbor : adjacent(vertex)) {
                reverse.addEdge(neighbor, vertex);
            }
        }

        return reverse;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int vertex = 0; vertex < vertices(); vertex++) {
            stringBuilder.append(vertex).append(": ");

            for(int neighbor : adjacent(vertex)) {
                stringBuilder.append(neighbor).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
