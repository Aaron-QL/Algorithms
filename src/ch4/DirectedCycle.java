package ch4;

import ch1.se3.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle (Digraph G) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.onStack = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!this.marked[v] && this.cycle == null) {
                this.dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        this.marked[v] = true;
        this.onStack[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) {
                return;
            }
            if (!this.marked[w]) {
                this.edgeTo[w] = v;
                this.dfs(G, w);
            } else if (this.onStack[w]){
                this.cycle = new Stack<Integer>();
                for (int x = v; x != w; x = this.edgeTo[x]) {
                    this.cycle.push(x);
                }
                this.cycle.push(w);
                this.cycle.push(v);
            }
        }
        this.onStack[v] = false;
    }

    public boolean hasCycle() {
        return this.cycle != null;
    }

    public Iterable<Integer> cycle() {
        return this.cycle;
    }
}
