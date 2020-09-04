package ch4.test;

import edu.princeton.cs.algs4.Stack;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int source;

    public DepthFirstPaths(Graph graph, int s) {
        if (s < 0 || s >= graph.vertices()) {
            throw new IllegalArgumentException();
        }
        this.source = s;
        this.marked = new boolean[graph.vertices()];
        this.edgeTo = new int[graph.vertices()];

        this.dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        this.marked[v] = true;
        for (int w : graph.adjacent(v)) {
            if (!this.marked[w]) {
                this.edgeTo[w] = v;
                this.dfs(graph, w);
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return this.marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        if (!this.hasPathTo(vertex)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();
        for (int i = vertex; i != this.source; i = this.edgeTo[i]) {
            path.push(i);
        }
        path.push(this.source);
        return path;
    }
}
