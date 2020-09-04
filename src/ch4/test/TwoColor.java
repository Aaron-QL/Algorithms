package ch4.test;

public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph graph) {
        this.marked = new boolean[graph.vertices()];
        this.color = new boolean[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!this.marked[i]) {
                this.dfs(graph, i);
            }
        }
    }

    private void dfs(Graph graph, int vertex) {
        this.marked[vertex] = true;

        for (int neighbor : graph.adjacent(vertex)) {
            if (!this.marked[neighbor]) {
                this.color[neighbor] = !this.color[vertex];
                this.dfs(graph, neighbor);
            } else if (this.color[neighbor] == this.color[vertex]) {
                this.isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
