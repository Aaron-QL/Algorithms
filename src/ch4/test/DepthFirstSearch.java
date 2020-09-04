package ch4.test;

public class DepthFirstSearch {
    private int count = 0;
    private boolean[] marked;

    public DepthFirstSearch(Graph graph, int s) {
        this.marked = new boolean[graph.vertices()];

        this.dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        this.marked[v] = true;
        this.count++;
        for (int w : graph.adjacent(v)) {
            if (!this.marked[w]) {
                this.dfs(graph, w);
            }
        }
    }

    public boolean marked(int vertex) {
        return marked[vertex];
    }

    public int count() {
        return count;
    }
}
