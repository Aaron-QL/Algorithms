package ch4.test;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph digraph, int source) {
        this.marked = new boolean[digraph.vertices()];
        this.dfs(digraph, source);
    }

    private void dfs(Digraph digraph, int vertex) {
        this.marked[vertex] = true;
        for (int neighbor : digraph.adjacent(vertex)) {
            if (!this.marked[neighbor]) {
                this.dfs(digraph, neighbor);
            }
        }
    }

    public DirectedDFS(Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.vertices()];

        for(int source : sources) {
            if (!marked[source]) {
                dfs(digraph, source);
            }
        }
    }

    public boolean marked(int vertex) {
        return marked[vertex];
    }

    public static void main(String[] args) {
        Digraph digraph = new Digraph(new In(args[0]));

        Bag<Integer> sources = new Bag<>();
        for(int i = 1; i < args.length; i++) {
            sources.add(Integer.parseInt(args[i]));
        }

        DirectedDFS reachable = new DirectedDFS(digraph, sources);

        for(int vertex = 0; vertex < digraph.vertices(); vertex++) {
            if (reachable.marked[vertex]) {
                StdOut.print(vertex + " ");
            }
        }

        StdOut.println();
    }
}
