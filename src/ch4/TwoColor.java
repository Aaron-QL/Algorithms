package ch4;

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph G) {
        this.marked = new boolean[G.V()];
        this.color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!this.marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!this.marked(w)) {
                this.color[w] = !color[v];
            } else if (this.color[w] == this.color[v]) {
                this.isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return this.isTwoColorable;
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
}
