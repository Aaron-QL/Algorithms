package ch4.se1;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.validateVertex(s);
        this.dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        this.count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!this.marked(w)) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        this.validateVertex(v);
        return marked[v];
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("");
        }
    }

    public int count() {
        return this.count;
    }
}
