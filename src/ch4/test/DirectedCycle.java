package ch4.test;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph digraph, int source) {
        this.marked = new boolean[digraph.vertices()];
        this.edgeTo = new int[digraph.vertices()];
        this.onStack = new boolean[digraph.vertices()];
        this.dfs(digraph, source);
    }

    public DirectedCycle(Digraph digraph) {
        onStack = new boolean[digraph.vertices()];
        edgeTo = new int[digraph.vertices()];
        marked = new boolean[digraph.vertices()];

        for(int vertex = 0; vertex < digraph.vertices(); vertex++) {
            if (!marked[vertex]) {
                dfs(digraph, vertex);
            }
        }
    }

    private void dfs(Digraph digraph, int vertex) {
        this.marked[vertex] = true;
        this.onStack[vertex] = true;
        for (int neighbor : digraph.adjacent(vertex)) {
            if (!this.marked[neighbor]) {
                this.edgeTo[neighbor] = vertex;
                this.dfs(digraph, neighbor);
            } else if (this.onStack[neighbor]) {
                this.cycle = new Stack<>();
                for (int v = vertex; v != neighbor; v = this.edgeTo[v]) {
                    this.cycle.push(v);
                }
                this.cycle.push(neighbor);
                this.cycle.push(vertex);
            }
        }

        this.onStack[vertex] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

}
